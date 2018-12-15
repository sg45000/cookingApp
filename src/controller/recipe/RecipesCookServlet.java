package controller.recipe;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Box;
import model.Recipe_Materials;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class RecipesCookServlet
 */
@WebServlet("/recipes/cook")
public class RecipesCookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipesCookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String _token =request.getParameter("_token");
		if(_token!=null && _token.equals(request.getSession().getId())) {
		    EntityManager em = DBUtil.createEM();
		    User u =(User)request.getSession().getAttribute("login_user");
		    Integer recipe_id=Integer.parseInt(request.getParameter("recipe_id"));
		    em.getTransaction().begin();

		    List<Recipe_Materials> rms = em.createNamedQuery("getMaterialsOfRecipe", Recipe_Materials.class)
		            .setParameter("recipe_id",recipe_id)
		            .getResultList();
		    Boolean commit_flag=true;
		    for(Recipe_Materials rm : rms) {
		        Integer material_id = rm.getMaterial_id();
		        Box b=null;
		        try {
		            b= em.createNamedQuery("cookFromMyBox", Box.class)
		                .setParameter("material_id", material_id)
		                .setParameter("user_id", u.getUser_id())
		                .getSingleResult();
		        }catch(Exception e) {
		            commit_flag=false;
		            break;
		        }
		        Double need_quantity = rm.getQuantity();
                Double current_quantity = b.getQuantity();
                Double after_quantity =current_quantity-need_quantity;

		        if(after_quantity<=0) {
		           em.remove(b);
		        }else {
		           b.setQuantity(after_quantity);

		        }
		    }

		    if(commit_flag==false) {

		        em.getTransaction().rollback();
		        em.close();
		        request.getSession().setAttribute("flush", "材料が足りませんでした。");
		        request.setAttribute("_token", request.getSession().getId());
		        response.sendRedirect(request.getContextPath()+"/recipes/index");
		    }else {

		        em.getTransaction().commit();
		        em.close();
		        request.getSession().setAttribute("flush", "料理が完成しました。");
		        response.sendRedirect(request.getContextPath()+"/top/index");
		    }
		}
	}

}
