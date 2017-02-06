package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PromoDao;

/**
 * Servlet implementation class NewPromo
 */
@WebServlet("/NewPromo")
public class NewPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPromo() {
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
		String promo = request.getParameter("promo");
		String tempLimit = request.getParameter("limit");
		String tempMinus = request.getParameter("minus");
		response.setCharacterEncoding("utf-8");
		PrintWriter outinlogin = response.getWriter();
		try {
			double limit = Double.valueOf(tempLimit);
			double minus = Double.valueOf(tempMinus);
			String scope = request.getParameter("scope");
			if(scope.equals("null")) {
				scope = "all";
			}
			PromoDao promoDao = new PromoDao();
			boolean flag = promoDao.insertPromo(promo, scope, tempLimit, tempMinus);
			if(flag) {
				outinlogin.print("<script>alert('添加成功！'); window.location='/Sell/"+request.getSession().getAttribute("authority")+"/dashboard.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
			else {
				outinlogin.print("<script>alert('添加失败，请联系管理员！'); window.location='/Sell"+request.getSession().getAttribute("authority")+"/dashboard.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
		}
		catch(NumberFormatException e){
		    //whatever you feel necessary to do when en empty string is presented
			outinlogin.print("<script>alert('你的输入数据格式有误！'); window.location='/Sell/root/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		
	}

}
