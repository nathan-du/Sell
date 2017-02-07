<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,dao.CartDao,java.io.PrintWriter;"%>
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>添加至购物车</title>
 </head>
 <body> 
  <%
   CartDao cart = new CartDao();
   String id = request.getParameter("id");
   String price = request.getParameter("price");
   String name = request.getParameter("name");
   String username = (String)session.getAttribute("username");
   String resid = request.getParameter("resid");
   boolean flag = cart.insertFood(id, username, name, price, resid);
   if(flag)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('添加成功！'); window.location='/Sell/user/showfood.jsp?resid="+ resid +"' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('添加失败！请联系管理员！'); window.location='/Sell//user/showfood.jsp?resid="+ resid +"' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
  %>
 
 </body>
</html>