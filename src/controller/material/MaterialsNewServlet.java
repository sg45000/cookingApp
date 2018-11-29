package controller.material;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MaterialsType;
import model.MaterialsUnit;

/**
 * Servlet implementation class MaterialsNewServlet
 */
@WebServlet("/materials/new")
public class MaterialsNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("types", MaterialsType.TYPES);
	    request.setAttribute("unit_names", MaterialsUnit.UNIT_NAMES);
	    request.setAttribute("_token", request.getSession().getId());
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/materials/new.jsp");
		rd.forward(request, response);
	}

}
