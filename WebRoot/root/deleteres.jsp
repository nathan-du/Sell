<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,dao.ResDao,java.io.PrintWriter;
;"%>
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>删除页面 </title>
 </head>
 <body> 
  <%
   ResDao res = new ResDao();
   String resid = request.getParameter("resid");
   boolean flag = res.delete(resid);
   if(flag)
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('删除成功！'); window.location='/Sell/root/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
		else
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter outinlogin = response.getWriter();
			outinlogin.print("<script>alert('删除失败！'); window.location='/Sell/root/dashboard.jsp' </script>");
			outinlogin.flush();
			outinlogin.close(); 
		}
  %>
 
 </body>
</html>