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
      <a class="navbar-brand" href="#">豆瓣小组</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">精选</a></li>
        <li><a href="#">文化</a></li>
        <li><a href="#">科技</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="小组、话题">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${sessionScope.user != null}">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.userInfo.nickname} <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="user/home">我的豆瓣</a></li>
                <li><a href="user/${sessionScope.user.id}">我的账户</a></li>
                <li><a href="static/j_spring_security_logout">登出</a></li>
              </ul>
            </li>
          </c:when>
          <c:otherwise>
            <li><a href="#">登录</a></li>
            <li><a href="#">注册</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>