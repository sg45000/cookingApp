package controller.topPage;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    //↓ログイン処理操作が完成したら。
	    /*
	    String _token= (String)request.getSession().getAttribute("_token");
	    if(_token!=null && _token.equals(request.getSession().getId())) {}
	    */
	        EntityManager em = DBUtil.createEM();
	        if(request.getSession().getAttribute("flush")!=null) {

	            request.setAttribute("flush",request.getSession().getAttribute("flush"));
	            request.getSession().removeAttribute("flush");
	        }
	        
	        


	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/top/index.jsp");
	    rd.forward(request,response);
	}

}
