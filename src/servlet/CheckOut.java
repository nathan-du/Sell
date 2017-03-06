package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
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
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String detail = request.getParameter("detail");
		OrderDao orderDao = new OrderDao();
		boolean flag = orderDao.checkOut(username, name, phone, address, detail);
		if(flag) {
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('Success!'); window.location='/Sell/user/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close();
		}
		else {
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('Fail!'); window.location='/Sell/user/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close();
		}
	}

}
