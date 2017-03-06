<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,dao.CartDao,model.Cart"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>购物车</title>

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

          <h2 class="sub-header">购物车列表</h2>
          <div class="table-responsive">
          <form class="navbar-form navbar-left" action="/Sell/AddPromo" method="post">
              <div class="form-group">
	            <input type="text" name="promo" placeholder="优惠码" class="form-control">
	            <button  type="submit" class="btn btn-info">使用优惠码</button>
	          </div>
            </form>
          <form class="navbar-form" action="/Sell/Update" method="post">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>名称</th>
                  <th>单价</th>
                  <th>数量</th>
                  <th>总价</th>
                </tr>
              </thead>
              <tbody>
                <% 
                	double sum = 0;
                	for(int i = 0; i < cartList.size(); i++) {
                	double amount = cartList.get(i).getCount()*cartList.get(i).getPrice();
                	sum += amount;
                %>
                <tr>              
                  <div id="div4" style="visibility:hidden;"><input type="text" name="cartId<%=i %>" value="<%=cartList.get(i).getCartid()%>" class="form-control"></div> 
                  <td><%=cartList.get(i).getName()%></td>
                  <td><%=cartList.get(i).getPrice()%></td>
                  <% if(cartList.get(i).getId() != null) { %><td><input type="text" name="count<%=i%>"  value="<%=cartList.get(i).getCount()%>"></td>
                  <%}
                  	else {
                  %>
                  <td><%=cartList.get(i).getCount()%><div id="div4" style="visibility:hidden;"><input type="text" name="count" value="<%=cartList.get(i).getCount() %>" class="form-control"></div></td>
                  <%} %>
                  <td><%=amount %></td>
                </tr>
                <% } %>
                <tr>
                  <td>总额</td>
                  <td></td>
                  <td></td>
                  <td><%=sum %></td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><a href="/Sell/user/checkout.jsp"><button type="button" class="btn btn-sm btn-danger">结账</button></a></td>
                </tr>
              </tbody>
            </table>
            <button  type="submit" class="btn btn-success">更新购物车</button>
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
