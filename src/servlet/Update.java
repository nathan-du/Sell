package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		CartDao cartDao = new CartDao();
		int index = cartDao.getNumber(username);
		String[] cartId = new String[index];
		double[] count = new double[index];
		PrintWriter outinlogin = response.getWriter();
		for(int i  = 0; i < index; i++) {
			cartId[i] = request.getParameter("cartId" + i);
			String tempCount = request.getParameter("count" + i);
			try {
				count[i] = Double.valueOf(tempCount);
			}
			catch(NumberFormatException e){
			    //whatever you feel necessary to do when en empty string is presented
				outinlogin.print("<script>alert('wrong data format'); window.location='/Sell/user/cart.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
				return;
			}
			
		}
		for(int i = 0; i < index; i++) {
			cartDao.updateFood(cartId[i], count[i]);
		}
		outinlogin.print("<script>alert('update success'); window.location='/Sell/user/cart.jsp' </script>");
		outinlogin.flush();
		outinlogin.close(); 
	}

}
