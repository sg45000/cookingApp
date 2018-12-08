package controller.box;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import util.GetMaterialsUtil;

/**
 * Servlet implementation class BoxNewServlet
 */
@WebServlet("/box/new")
public class BoxNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoxNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em =DBUtil.createEM();

        GetMaterialsUtil.getMaterials(em, request);
        request.setAttribute("_token", request.getSession().getId());
        em.close();

        RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/box/new.jsp");
        rd.forward(request, response);
	}

}
