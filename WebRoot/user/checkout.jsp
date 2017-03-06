<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,dao.CartDao,model.Cart"%>
<!DOCTYPE html>
<style type="text/css">
ul,li{padding:0; margin:0; }
ul{list-style:none;}
</style>
<script type="text/javascript"> 
function show_confirm() 
{ 
var r=confirm("确认付款了么？"); 
if (r==true) 
  { 
  return true;
  } 
else 
  { 
  alert("下单失败，请付款!");
  return false;
  } 
} 
</script> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>结账</title>

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
    	CartDao cart = new CartDao();
        String username = (String)session.getAttribute("username");
        ArrayList<Cart> cartList = cart.seletAll(username);
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
            <li><a href="cart.jsp">购物车</a></li>
            <li><a href="/Sell/logout.jsp">登出</a></li>
          </ul>
          <form class="navbar-form navbar-right" action = "/Sell/SearchFood" method="post">
            <input type="text" name="name" class="form-control" placeholder="输入商品名称">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="dashboard.jsp">餐馆列表 </a></li>
            <li><a href="showorder.jsp">我的订单</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">  

          <h2 class="sub-header">结账</h2>
          <div class="table-responsive">
          	<form class="navbar-form navbar-left" action="/Sell/CheckOut" method="post">
            <ul>
            <li>
	          <div class="form-group">
	            <input type="text" name="name" placeholder="收餐人姓名" class="form-control">
	          </div>
	          <div class="form-group"></div>
	          <div class="form-group">
	            <input type="text" name="phone" placeholder="联系电话" class="form-control">
	          </div>
	        </li>
        	<li><div class="form-group"></div><a></a></li>
        	<li>
            <div class="form-group">
              <input type="text" name="address" placeholder="地址" class="form-control">
            </div>
            <div class="form-group"></div>
	          <div class="form-group">
	            <input type="text" name="detail" placeholder="备注" class="form-control">
	          </div>
            </li>
            <li><div class="form-group"></div><a></a></li>
            </ul>
            <button type="submit" onclick="return show_confirm()" class="btn btn-success">结账</button> 
          	</form>
          </div>
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
