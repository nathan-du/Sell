<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,dao.OrderDao,java.io.PrintWriter;
;"%>
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>接受订单 </title>
 </head>
 <body> 
  <%
   OrderDao orderDao = new OrderDao();
   String orderId = request.getParameter("orderid");
   boolean flag = orderDao.changeStatus(orderId, 1);
   if(flag)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('接单成功！'); window.location='/Sell/res/processing.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('接单失败！'); window.location='/Sell/res/processing.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
  %>
 
 </body>
</html>