package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;

/**
 * Servlet implementation class Rate
 */
@WebServlet("/Rate")
public class Rate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rate() {
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
		String point = request.getParameter("point");
		String rate = request.getParameter("rate");
		String orderId = request.getParameter("orderId");
		OrderDao order = new OrderDao();
		boolean flag = order.rate(orderId, point, rate);
		PrintWriter outinlogin = response.getWriter();
		if(flag) {
			outinlogin.print("<script>alert('Rate Success!'); window.location='/Sell/user/showorder.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else {
			outinlogin.print("<script>alert('Rate Failed!'); window.location='/Sell/user/showorder.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
	}

}
