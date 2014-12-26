<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">豆比小组</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">精选</a></li>
        <li><a href="#">文化</a></li>
        <li><a href="#">科技</a></li>
      </ul>
      <form class="navbar-form navbar-left" action="group/search" method="get" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="小组、话题">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${sessionScope.user != null}">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.nickname} <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="group">我的小组</a></li>
                <li><a href="user/${sessionScope.user.userId }">我的主页</a>
                <li><a href="user/userinfo">修改个人信息</a></li>
                <li><a href="user/avatar">修改／上传头像</a></li>
                <li><a href="static/j_spring_security_logout?redirect=index.html">登出</a></li>
              </ul>
            </li>
          </c:when>
          <c:otherwise>
            <li><a href="user/login">登录</a></li>
            <li><a href="user/register">注册</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>