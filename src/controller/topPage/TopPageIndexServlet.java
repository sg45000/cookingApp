package controller.topPage;

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

import model.B_RM;
import model.Box;
import model.BoxMaterials;
import model.CountBoxIn;
import model.MateQuan;
import model.Materials;
import model.Recipe_Materials;
import model.Recipes;
import model.RecomendRecipe;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/top/index")
public class TopPageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
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



	        User u=(User)request.getSession().getAttribute("login_user");


	        List<CountBoxIn> cbis = em.createNamedQuery("getCountBoxIn", CountBoxIn.class)
	            .setParameter("user_id",u.getUser_id())
	            .getResultList();

	        List<RecomendRecipe> rrList = new ArrayList<RecomendRecipe>();

	        for(CountBoxIn cbi:cbis) {
	            RecomendRecipe rr=new RecomendRecipe();
	            List<Recipe_Materials> rmList =em.createNamedQuery("getMaterialsOfRecipe", Recipe_Materials.class)
	                    .setParameter("recipe_id",  cbi.getRecipe_id())
	                    .getResultList();

	            B_RM b_rm =new B_RM();

	            List<MateQuan> mqList = new ArrayList<MateQuan>();
	            for(Recipe_Materials rm : rmList) {
	                MateQuan mq =new MateQuan();
	                try {
	                     b_rm = em.createNamedQuery("getMateQuan",B_RM.class)
	                        .setParameter("user_id", u.getUser_id())
	                        .setParameter("recipe_id", cbi.getRecipe_id())
	                        .setParameter("material_id", rm.getMaterial_id())
	                        .getSingleResult();
	                     mq.setEnough(true);

	                     mq.setB_quan(b_rm.getB_quan());
	                }catch(Exception e) {
	                    mq.setB_quan(0.0);
	                    mq.setEnough(false);
	                }
	                mq.setRm_quan(rm.getQuantity());
	                mq.setM(em.find(Materials.class, rm.getMaterial_id()));
	                mqList.add(mq);


	            }
	            Integer count_rm = rmList.size();
	            Double ratio = (double)cbi.getCount()/count_rm;
	            rr.setMqList(mqList);
	            rr.setR(em.find(Recipes.class,cbi.getRecipe_id()));
	            rr.setRatio(ratio*100.0);
	            rrList.add(rr);

	        }
	        request.setAttribute("rrList", rrList);

	        //boxIndexServletからもってきた
	        List<Materials> materials =  em.createNamedQuery("getMyMaterials",Materials.class).setParameter("user_id",u.getUser_id() ).getResultList();
	        List<BoxMaterials> bmList = new ArrayList<BoxMaterials>();
	        for(Materials material: materials) {
	            Box box= em.createNamedQuery("getMyBox", Box.class).setParameter("material_id", material.getMaterial_id()).setParameter("user_id", u.getUser_id()).getSingleResult();
	            BoxMaterials bm = new BoxMaterials(material,box);
	            bmList.add(bm);

	        }
	        request.setAttribute("bmList",bmList);

	    em.close();
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/top/index.jsp");
	    rd.forward(request,response);
	}

}
