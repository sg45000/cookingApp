package controller.box;

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

import model.Box;
import model.BoxMaterials;
import model.Materials;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class BoxIndexServlet
 */
@WebServlet("/box/index")
public class BoxIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoxIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEM();
	    User u=(User)request.getSession().getAttribute("login_user");
	    List<Materials> materials =  em.createNamedQuery("getMyMaterials",Materials.class).setParameter("user_id",u.getUser_id() ).getResultList();
	    List<BoxMaterials> bmList = new ArrayList<BoxMaterials>();
	    for(Materials material: materials) {
	        Box box= em.createNamedQuery("getMyBox", Box.class).setParameter("material_id", material.getMaterial_id()).setParameter("user_id", u.getUser_id()).getSingleResult();
	        BoxMaterials bm = new BoxMaterials(material,box);
	        bmList.add(bm);

	    }
	    request.setAttribute("bmList",bmList);

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/box/index.jsp");
	    rd.forward(request, response);
	}


}
