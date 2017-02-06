<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,dao.FoodDao,dao.ResDao,model.Food"%>
<!DOCTYPE html>
<style type="text/css">
ul,li{padding:0; margin:0; }
ul{list-style:none;}
</style>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>管理员</title>

    <!-- Bootstrap core CSS -->
    <link href="/Sell/styles/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/Sell/styles/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	<%
        String username = (String)session.getAttribute("username");
        ResDao res = new ResDao();
        String resid = res.getResid(username);
     %>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">外卖</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="dashboard.jsp">概况</a></li>
            <li><a href="newres.jsp">待处理订单</a></li>
            <li class="active"><a href="newfood.jsp">添加美食</a></li>
            <li><a href="newpromo.jsp?resid=<%=resid%>">添加优惠码</a></li>
            <li><a href="showorder.jsp?resid=<%=resid%>">显示所有订单</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">餐馆管理</h1>

         

          <h2 class="sub-header">添加美食</h2>
          <form class="navbar-form navbar-left" action="/Sell/NewFood" method="post">
            <ul>
            <li>
	          <div class="form-group">
	            <input type="text" name="name" placeholder="美食名称" class="form-control">
	          </div>
	          <div class="form-group"></div>
	          <div class="form-group">
	            <input type="text" name="price" placeholder="价格" class="form-control">
	          </div>
	        </li>
        	<li><div class="form-group"></div><a></a></li>
        	<li>
            <div class="form-group">
              <input type="text" name="detail" placeholder="描述" class="form-control">
            </div>
            <div id="div4" style="visibility:hidden;"><input type="text" name="resid" value="<%=resid %>" class="form-control"></div> 
            </li>
            <li><div class="form-group"></div><a></a></li>
            </ul>
            <button type="submit" class="btn btn-success">添加</button> 
          	</form>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
