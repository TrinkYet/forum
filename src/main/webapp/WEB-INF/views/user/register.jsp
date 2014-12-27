<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<meta charset="UTF-8">
<%@ include file="/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/home.css">
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
</head>
<body>

  <%--   <div class="container col-md-6 col-md-offset-3">
        <h2 class="form-signin-heading text-center">请注册</h2>
        <form id="form" action="user/register" class="form-horizontal form-signin" method="post">
            <input type="hidden" name="op" value="register">
            <div class="form-group">
              <label class="col-sm-2 control-label" for="nickname">昵称</label>
              <div class="col-sm-8">
                  <input id="nickname" name="nickname" type="text" class="form-control" required="true">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="account">邮箱</label>
              <div class="col-sm-8">
                  <input id="account" name="email" type="email" class="form-control" required="true">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="passwd">密码</label>
              <div class="col-sm-8">
                <input id="passwd" name="password" type="password" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="passwd2">确认密码</label>
              <div class="col-sm-8">
                <input id="passwd2" name="password2" type="password" class="form-control">
              </div>
            </div>
            <div class="form-group text-center">
                <div class="col-sm-offset-2 col-sm-8 clearfix">
                    <input type="submit" value="注册" class="btn btn-primary col-sm-4 col-sm-offset-4">
                   
                </div>
            </div>
        </form>
    </div> --%>

	<div class="container">
		<div id="signupbox" style="margin-top: 50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">注册</div>
					<div style="float: right; font-size: 85%; position: relative; top: -10px">
						<a id="signinlink" href="user/login">登陆</a>
					</div>
				</div>
				<div class="panel-body">
					<form id="form" action="user/register" method="post" class="form-horizontal" role="form">

						<div id="signupalert" style="display: none"
							class="alert alert-danger">
							<p>Error:</p>
							<span></span>
						</div>

						<div class="form-group">
							<label for="email" class="col-md-3 control-label">邮箱</label>
							<div class="col-md-9">
								<input id="account" type="email" class="form-control" name="email"
									placeholder="Email Address">
							</div>
						</div>

						<div class="form-group">
							<label for="nickname" class="col-md-3 control-label">昵称</label>
							<div class="col-md-9">
								<input id="nickname" type="text" class="form-control" name="nickname"
									placeholder="Nickname">
							</div>
						</div>
						
						<div class="form-group">
							<label for="passwd" class="col-md-3 control-label">密码</label>
							<div class="col-md-9">
								<input id="passwd" type="password" class="form-control" name="password"
									placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label for="passwd2" class="col-md-3 control-label">确认密码</label>
							<div class="col-md-9">
								<input id="passwd2" type="password" class="form-control" name="password2"
									placeholder="Confirm Password">
							</div>
						</div>
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<input id="btn-signup" type="submit" class="btn btn-info" value="注册">
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>




	<script src="js/messages_zh.min.js"></script>
</body>
</html>