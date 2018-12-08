package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import util.DBUtil;
import util.EncryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("hasError", false);

		if(request.getSession().getAttribute("flush")!=null) {
		    request.setAttribute("flush", request.getSession().getAttribute("flush"));
		    request.getSession().removeAttribute("flush");
		}

	    RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Boolean check_result =false;
	    String _token=request.getParameter("_token");
	    if(_token!=null && _token.equals(request.getSession().getId())) {
	        EntityManager em = DBUtil.createEM();
	        String email = request.getParameter("email");
	        String plain_pass =request.getParameter("plain_pass");
	        User u = null;
	        if(email!=null && !email.equals("") && plain_pass!=null && !plain_pass.equals("")) {
	            String password=EncryptUtil.getPasswordEncrypt(plain_pass,
	                    (String)this.getServletContext().getAttribute("salt"));
	            try {
	                u=em.createNamedQuery("checkLoginEmailAndPassword",User.class)
	                    .setParameter("email", email)
	                    .setParameter("password", password)
	                    .getSingleResult();
	            }catch(NoResultException ex) {}

	            em.close();

	            if(u!=null) {
	                check_result=true;
	            }


	        }
	        if(!check_result) {
	            request.setAttribute("hasError", true);
	            request.setAttribute("email", email);
	            request.setAttribute("_token", request.getSession().getId());
	            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
	            rd.forward(request, response);
	        }else {
	            request.getSession().setAttribute("login_user", u);
	            request.getSession().setAttribute("flush", "ログインできました。");
	            response.sendRedirect(request.getContextPath()+"/top/index");
	        }

	    }


	}

}
