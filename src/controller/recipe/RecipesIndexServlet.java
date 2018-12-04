package controller.recipe;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Materials;
import model.Recipe_Materials;
import model.Recipes;
import util.DBUtil;

/**
 * Servlet implementation class RecipesIndexServlet
 */
@WebServlet("/recipes/index")
public class RecipesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEM();

		int page=1;
		try {
		    page=Integer.parseInt(request.getParameter("page"));
		}catch(Exception e) {}

		List<Recipes> recipes =em.createNamedQuery("getAllRecipes",Recipes.class)
		        .setFirstResult((page-1)*10)
                .setMaxResults(10)
                .getResultList();
		for(Recipes recipe:recipes) {
		    List<Recipe_Materials> rms=em.createNamedQuery("getMaterialsOfRecipe",Recipe_Materials.class)
            .setParameter("recipe_id", recipe.getRecipe_id()).getResultList();
		    for(Recipe_Materials rm : rms ) {
		        request.setAttribute("rm"+rm.getMaterial_id()+"of"+rm.getRecipe_id(), rm);
		    }


		    List<Materials> m=em.createNamedQuery("getMaterials",Materials.class)
            .setParameter("recipe_id",recipe.getRecipe_id())
            .getResultList();
		    request.setAttribute("materials_"+recipe.getRecipe_id().toString(),m);

		    long c=em.createNamedQuery("countMaterialsOfRecipe", Long.class)
            .setParameter("recipe_id", recipe.getRecipe_id())
            .getSingleResult();
		    request.setAttribute("count_"+recipe.getRecipe_id().toString(),c);
		}

		long count_recipes = em.createNamedQuery("getCountRecipes", Long.class)
		        .getSingleResult();

		em.close();


		request.setAttribute("recipes", recipes);
		request.setAttribute("count_recipes", count_recipes);

		if(request.getSession().getAttribute("flash")!=null) {
		    request.setAttribute("flash", request.getSession().getAttribute("flash"));
		    request.getSession().removeAttribute("flash");
		}


		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/recipes/index.jsp");
		rd.forward(request, response);

	}

}
