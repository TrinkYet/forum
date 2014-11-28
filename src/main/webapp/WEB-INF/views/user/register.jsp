<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<meta charset="UTF-8">
<%@ include file="/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
    <div class="container col-md-6 col-md-offset-3">
        <h2 class="form-signin-heading text-center">请注册</h2>
        <form id="form" action="user/register" class="form-horizontal form-signin" method="post">
            <input type="hidden" name="op" value="register">
            <div class="form-group">
              <label class="col-sm-2 control-label" for="nickname">Nickname</label>
              <div class="col-sm-8">
                  <input id="nickname" name="nickname" type="text" class="form-control" required="true">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="account">Email</label>
              <div class="col-sm-8">
                  <input id="account" name="email" type="email" class="form-control" required="true">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="passwd">Password</label>
              <div class="col-sm-8">
                <input id="passwd" name="password" type="password" class="form-control">
              </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8">
                    <input type="submit" value="注册" class="btn btn-default">
                    <a href="index.html" class="btn btn-info">返回</a>
                </div>
            </div>
        </form>
    </div>

</body>
</html>