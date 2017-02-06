package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao user = new UserDao();
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String flag = user.toLogin(username, password);
		if(flag.equals("none")) {
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('用户名密码不匹配！'); window.location='login.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else 
		{  
			session.setAttribute("username", username); 
			session.setAttribute("authority", flag);
			if(flag.equals("root")) response.sendRedirect("/Sell/root/dashboard.jsp");
			else if(flag.equals("res")) response.sendRedirect("/Sell/res/dashboard.jsp");
			else if(flag.equals("user")) response.sendRedirect("/Sell/user/dashboard.jsp");
		}
	}

}
