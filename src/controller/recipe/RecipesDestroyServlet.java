package controller.recipe;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Recipe_Materials;
import model.Recipes;
import util.DBUtil;

/**
 * Servlet implementation class RecipesDestroyServlet
 */
@WebServlet("/recipes/destroy")
public class RecipesDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipesDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

            EntityManager em =DBUtil.createEM();
            Integer recipe_id=Integer.parseInt(request.getParameter("recipe_id"));
            Recipes r = em.find(Recipes.class, recipe_id);
            List<Recipe_Materials> rms =em.createNamedQuery("getMaterialsOfRecipe",Recipe_Materials.class)
                    .setParameter("recipe_id",recipe_id)
                    .getResultList();

           em.getTransaction().begin();
           for(Recipe_Materials rm :rms) {
               em.remove(rm);
           }
            em.remove(r);
            em.getTransaction().commit();;
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");

        response.sendRedirect(request.getContextPath()+"/top/index");
	}

}
