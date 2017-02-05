package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		String authority = "user";
		UserDao user = new UserDao();
		boolean flag = user.insertUser(username, password, authority);
		if(flag)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('注册成功！'); window.location='/Sell/login.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('用户名已被占用！'); window.location='/Sell/signup.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
	}

}
