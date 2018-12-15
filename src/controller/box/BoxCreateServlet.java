package controller.box;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Box;
import model.MaterialsType;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class BoxCreateServlet
 */
@WebServlet("/box/create")
public class BoxCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoxCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User u=(User)request.getSession().getAttribute("login_user");
	    String _token = request.getParameter("_token");
        if(_token !=null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEM();
            String[] types=MaterialsType.TYPES;
            int error_count=0;

            em.getTransaction().begin();
            for(String type: types) {
                String[] ids=request.getParameterValues(type+"_id");
                String[] quantities=request.getParameterValues(type+"_quantity");
                List<String> quantityList =new ArrayList<String>();
                int count=0;
                if(ids!=null) {
                    for(String quantity :quantities) {
                        if(!quantity.equals("")) {
                            quantityList.add(quantity);
                            count++;
                        }
                    }
                    if(ids.length!=count) {
                        request.setAttribute("flush", "チェックを入れた材料には、量を指定してください。");
                        error_count++;
                        break;
                    }else {

                        for(int i=0;i<ids.length;i++) {
                            Integer id_i = Integer.parseInt(ids[i]);
                            Double quantity = Double.valueOf(quantityList.get(i));
                            Box have_b=null;
                            try {
                             have_b=em.createNamedQuery("checkBoxMaterial", Box.class)
                                .setParameter("material_id", id_i)
                                .setParameter("user_id", u.getUser_id())
                                .getSingleResult();
                            }catch(Exception e) {}
                            if(have_b!=null) {
                                have_b.setQuantity(quantity+have_b.getQuantity());
                                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                                have_b.setUpdated_at(currentTime);
                            }else {
                                Box b = new Box();
                                b.setMaterial_id(id_i);
                                b.setUser_id(u.getUser_id());
                                b.setQuantity(quantity);
                                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                                b.setCreated_at(currentTime);
                                b.setUpdated_at(currentTime);
                                em.persist(b);
                            }
                        }
                    }
                }
            }
                if(error_count>0) {

                    em.getTransaction().rollback();
                    em.close();
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/box/new");
                    rd.forward(request, response);
                }else {
                    em.getTransaction().commit();
                    em.close();
                    request.getSession().setAttribute("flush", "食材を冷蔵庫に保存しました。");
                    response.sendRedirect(request.getContextPath()+"/top/index");
                }


        }

	}

}
