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
import dao.PromoDao;

/**
 * Servlet implementation class AddPromo
 */
@WebServlet("/AddPromo")
public class AddPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPromo() {
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
		String promo = request.getParameter("promo");
		PromoDao promoDao = new PromoDao();
		CartDao cartDao = new CartDao();
		String scope = promoDao.getScope(promo);
		double lmt = promoDao.getLmt(promo);
		double minus = promoDao.getMinus(promo);
		PrintWriter outinlogin = response.getWriter();
		if(scope != null && scope.equals("all")) {
			double sum = cartDao.getSum(username);
			if(sum >= lmt) {
				cartDao.insertPromo(username, 1, promo, -minus);
				outinlogin.print("<script>alert('Add Success'); window.location='/Sell/user/cart.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
			else {
				outinlogin.print("<script>alert('Your promotion is unavailable'); window.location='/Sell/user/cart.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
		}
		else if(scope != null){
			double sum = cartDao.getSum(username, scope);
			if(sum >= lmt) {
				cartDao.insertPromo(username, 1, promo, -minus);
				outinlogin.print("<script>alert('Add Success'); window.location='/Sell/user/cart.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
			else {
				outinlogin.print("<script>alert('Your promotion is unavailable'); window.location='/Sell/user/cart.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
		}
		else {
			outinlogin.print("<script>alert('Your promotion is unavailable'); window.location='/Sell/user/cart.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
	}

}
