package controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import util.DBUtil;
import util.EncryptUtil;
import validators.UserValidator;

/**
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token!=null && _token.equals(request.getSession().getId())) {
		    EntityManager em = DBUtil.createEM();
		    User u = new User();
		    String name=request.getParameter("name");
		    u.setName(name);
		    String email=request.getParameter("email");
		    u.setEmail(email);
		    String plain_pass=request.getParameter("plain_pass");
		    String plain_pass_confirm =request.getParameter("plain_pass_confirm");
		    u.setPassword(EncryptUtil.getPasswordEncrypt(plain_pass,
                    (String)this.getServletContext().getAttribute("salt"))
                    );
		    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            u.setCreated_at(currentTime);
            u.setUpdated_at(currentTime);

            List<String> errors = UserValidator.validate(u,true,plain_pass,plain_pass_confirm);
            if(errors.size()>0) {

             em.close();
             request.setAttribute("errors", errors);
             request.setAttribute("_token", request.getSession().getId());
             request.setAttribute("user", u);
             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
             rd.forward(request, response);

            }else {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            request.getSession().setAttribute("login_user", u);
            response.sendRedirect(request.getContextPath()+"/top/index");
            }

		}
	}

}
