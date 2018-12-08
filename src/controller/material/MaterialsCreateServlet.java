package controller.material;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Materials;
import model.MaterialsType;
import model.MaterialsUnit;
import util.DBUtil;
import validators.MaterialValidator;

/**
 * Servlet implementation class MaterialsCreateServlet
 */
@WebServlet("/materials/create")
public class MaterialsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String _token = request.getParameter("_token");
        if(_token !=null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEM();
            Materials m = new Materials();
            String name = request.getParameter("name");
            m.setName(name);
            String unit = request.getParameter("unit");
            m.setUnit(unit);
            String type = request.getParameter("type");
            m.setType(type);
            String use_limit=request.getParameter("use_limit");
            m.setUse_limit(use_limit);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            List<String> errors = MaterialValidator.validate(name,unit,type);
            if(errors.size()>0) {
                em.close();
                request.setAttribute("errors", errors);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("name", name);
                request.setAttribute("use_limit", use_limit);
                request.setAttribute("unit_names", MaterialsUnit.UNIT_NAMES);
                request.setAttribute("types", MaterialsType.TYPES);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/materials/new.jsp");
                rd.forward(request, response);
            }else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "食材の新規登録が完了しました。");
            }
        }
        response.sendRedirect(request.getContextPath()+"/top/index");


	}

}
