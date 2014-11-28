<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>userinfo</title>
<meta charset="UTF-8">
<%@ include file="/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
	<div class="container col-md-6 col-md-offset-3">
		<h2 class="form-signin-heading text-center">用户信息</h2>
		<form id="form" action="user" class="form-horizontal form-signin" method="post">
			<div class="form-group">
			  <label class="col-sm-2 control-label" for="phone">手机：</label>
			  <div class="col-sm-8">
			  	<input id="phone" name="phone" type="phone" class="form-control">
			  </div>
			</div>
			<div class="form-group">
              <label class="col-sm-2 control-label" for="address">地址：</label>
              <div class="col-sm-8">
                <input id="address" name="address" type="address" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" for="gender">性别：</label>
              <div class="col-sm-8">
                <input id="gender" name="gender" type="gender" class="form-control">
              </div>
            </div>
            <!-- <div class="form-group">
              <label class="col-sm-2 control-label" for="birthday">生日：</label>
              <div class="col-sm-8">
                <input id="birthday" name="birthday" type="birthday" class="form-control">
              </div>
            </div> -->
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<input type="submit" value="保存" class="btn btn-default">
					<a href="index.html" class="btn btn-info">返回</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>