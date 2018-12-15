package controller.recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IndexRecipe;
import model.Materials;
import model.MaterialsOfRecipe;
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
         if(request.getSession().getAttribute("flush")!=null) {
             request.setAttribute("flush",request.getSession().getAttribute("flush"));
             request.getSession().removeAttribute("flush");
         }

		int page=1;
		try {
		    page=Integer.parseInt(request.getParameter("page"));
		}catch(Exception e) {}

		List<Recipes> recipes =em.createNamedQuery("getAllRecipes",Recipes.class)
		        .setFirstResult((page-1)*10)
                .setMaxResults(10)
                .getResultList();
		List<IndexRecipe> indexList = new ArrayList<IndexRecipe>();
		for(Recipes recipe:recipes) {
		    IndexRecipe index = new IndexRecipe();

		    List<Recipe_Materials> rms=em.createNamedQuery("getMaterialsOfRecipe",Recipe_Materials.class)
            .setParameter("recipe_id", recipe.getRecipe_id()).getResultList();
		    List<MaterialsOfRecipe> morList = new ArrayList<MaterialsOfRecipe>();
		    for(Recipe_Materials rm : rms) {
		        Materials m=em.createNamedQuery("getMaterials",Materials.class)
		                .setParameter("material_id",rm.getMaterial_id())
		                .getSingleResult();
		        MaterialsOfRecipe mor = new MaterialsOfRecipe(m,rm.getQuantity(),recipe.getRecipe_id());
		        morList.add(mor);
		    }
	        index.setR(recipe);
		    index.setMorList(morList);
		    indexList.add(index);


		}

		em.close();


		request.setAttribute("indexList",indexList);
		request.setAttribute("_token", request.getSession().getId());

		if(request.getSession().getAttribute("flash")!=null) {
		    request.setAttribute("flash", request.getSession().getAttribute("flash"));
		    request.getSession().removeAttribute("flash");
		}


		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/recipes/index.jsp");
		rd.forward(request, response);

	}

}
