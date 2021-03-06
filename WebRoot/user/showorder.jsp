<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,dao.OrderDao,dao.ResDao,model.Order"%>
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

    <title>我的订单</title>

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

         

          <h2 class="sub-header">订单列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>姓名</th>
                  <th>地址</th>
                  <th>联系电话</th>
                  <th>食物名字</th>
                  <th>总价</th>
                  <th>备注</th>
                  <td>状态</td>
                </tr>
              </thead>
              <tbody>
                <% 
                	OrderDao order = new OrderDao();
                	ArrayList<Order> orderList = order.selectOrderByUN(username);
                	for(int i = 0; i < orderList.size(); i++) {
                %>
                <tr>
                  <td><%=orderList.get(i).getOrderid()%></td>
                  <td><%=orderList.get(i).getName()%></td>
                  <td><%=orderList.get(i).getAddress()%></td>
                  <td><%=orderList.get(i).getPhone()%></td>
                  <td><%=orderList.get(i).getFood()%></td>
                  <td><%=orderList.get(i).getPrice()%></td>
                  <td><%=orderList.get(i).getDetail()%></td>
                  <% if(orderList.get(i).getStatus() == 0) {%>
                  <td>待接单</td>
                  <%} else if(orderList.get(i).getStatus() == 1) {%>
                  <td>已接单</td>
                  <%} else if(orderList.get(i).getStatus() == 2) {%>
                  <td><a href="/Sell/user/confirm.jsp?orderId=<%=orderList.get(i).getOrderid()%>"><button type="button" class="btn btn-sm btn-warning">确认收货</button></a></td>
                  <%} else if(orderList.get(i).getStatus() == 4){%>
                  <td><a href="/Sell/user/rateOrder.jsp?orderId=<%=orderList.get(i).getOrderid()%>"><button type="button" class="btn btn-sm btn-warning">评价</button></a></td>
                  <%}else { %>
                  <td>已评价</td>
                  <%} %>
                </tr>
                <% } %>
              </tbody>
            </table>
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
