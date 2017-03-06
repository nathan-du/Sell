<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,dao.OrderDao,java.io.PrintWriter;
;"%>
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>确认收货页面 </title>
 </head>
 <body> 
  <%
   OrderDao orderDao = new OrderDao();
   String orderId = request.getParameter("orderId");
   boolean flag = orderDao.changeStatus(orderId, 4);
   if(flag)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('收货成功！'); window.location='/Sell/user/showorder.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('收货失败！'); window.location='/Sell/user/showorder.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
  %>
 
 </body>
</html>