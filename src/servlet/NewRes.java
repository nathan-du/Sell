package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ResDao;
import dao.UserDao;

/**
 * Servlet implementation class NewRes
 */
@WebServlet("/NewRes")
public class NewRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRes() {
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
		String resname = request.getParameter("resname");
		String legalname = request.getParameter("legalname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String scope = request.getParameter("scope");
		String authority = "res";
		UserDao user = new UserDao();
		ResDao res = new ResDao();
		boolean flag2 = res.insertRes(username, legalname, resname, phone, address, scope);
		boolean flag1 = user.insertUser(username, password, authority);
		if(flag1 && flag2)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('添加成功！'); window.location='/Sell/root/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else if(!flag1)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('用户名已被占用！'); window.location='/Sell/root/newres.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else if(!flag2)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('崩了，请联系管理员！'); window.location='/Sell/root/newres.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
	}

}
