package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDao;

/**
 * Servlet implementation class NewFood
 */
@WebServlet("/NewFood")
public class NewFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFood() {
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
		String name = request.getParameter("name");
		String tempPrice = request.getParameter("price");
		String detail = request.getParameter("detail");
		String resid = request.getParameter("resid");
		response.setCharacterEncoding("utf-8");
		PrintWriter outinlogin = response.getWriter();
		try {
			double price = Double.valueOf(tempPrice);
			FoodDao food = new FoodDao();
			boolean flag = food.insertFood(name, resid, tempPrice, detail);
			if(flag) {
				outinlogin.print("<script>alert('添加成功！'); window.location='/Sell/res/dashboard.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
			else {
				outinlogin.print("<script>alert('添加失败，请联系管理员！'); window.location='/Sell/res/dashboard.jsp' </script>");
				outinlogin.flush();
				outinlogin.close(); 
			}
		}
		catch(NumberFormatException e){
		    //whatever you feel necessary to do when en empty string is presented
			outinlogin.print("<script>alert('你的输入数据格式有误！'); window.location='/Sell/res/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
	}

}
