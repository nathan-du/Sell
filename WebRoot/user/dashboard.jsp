<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,dao.ResDao,model.Res"%>
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

    <title>用户</title>

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
            <li class="active"><a href="#">餐馆列表 <span class="sr-only">(current)</span></a></li>
            <li><a href="showorder.jsp">我的订单</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

         

          <h2 class="sub-header">餐馆列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>餐馆名字</th>
                  <th>联系电话</th>
                  <th>地址</th>
                  <th>主营范围</th>
                  <th>浏览店铺</th>
                </tr>
              </thead>
              <tbody>
                <% 
                	ResDao res = new ResDao();
                	ArrayList<Res> resList = res.selectAllResForUser();
                	for(int i = 0; i < resList.size(); i++) {
                %>
                <tr>
                  <td><%=resList.get(i).getResId()%></td>
                  <td><%=resList.get(i).getResName()%></td>
                  <td><%=resList.get(i).getPhone()%></td>
                  <td><%=resList.get(i).getAddress()%></td>
                  <td><%=resList.get(i).getScope()%></td>
                  <td><a href="/Sell/user/showfood.jsp?resid=<%=resList.get(i).getResId()%>"><button type="button" class="btn btn-sm btn-warning">浏览</button></a></td>
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
