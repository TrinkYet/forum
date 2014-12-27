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
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css">
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
</head>
<body>
	<div class="container">
		<div id="signupbox" style="margin-top: 50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">个人信息</div>
				</div>
				<div class="panel-body">
					<form id="form" action="user/userinfo" method="post" class="form-horizontal" role="form">

						<div id="signupalert" style="display: none"
							class="alert alert-danger">
							<p>Error:</p>
							<span></span>
						</div>

						<div class="form-group">
							<label for="gender" class="col-md-3 control-label">性别</label>
							<div class="col-md-9">
								<c:choose>
					              <c:when test="${userInfo == null}">
					                <input id="gender" name="gender" type="gender" class="form-control">
					              </c:when>
					              <c:otherwise>
					                <input id="gender" name="gender" type="gender" class="form-control" value="${userInfo.gender }">
					              </c:otherwise>
					            </c:choose>
							</div>
						</div>

						<div class="form-group">
							<label for="birthday" class="col-md-3 control-label">生日</label>
							<div class="col-md-9">
							  <c:choose>
				              	<c:when test="${userInfo == null }">
				                	<input id="birthday" name="birthday" type="date" class="form-control">
				                </c:when>
				                <c:otherwise>
				                	<input id="birthday" name="birthday" type="date" class="form-control" value="${userInfo.birthday }">
				                </c:otherwise>
				              </c:choose>
							</div>
						</div>
						
						<div class="form-group">
							<label for="residence" class="col-md-3 control-label">现居地</label>
							<div class="col-md-9">
								<c:choose>
					              <c:when test="${userInfo == null }">
					                <input id="residence" name="residence" type="text" class="form-control">
					              </c:when>
					              <c:otherwise>
					                <input id="residence" name="residence" type="text" class="form-control" value="${userInfo.residence }">
					              </c:otherwise>
					            </c:choose>
							</div>
						</div>
						<div class="form-group">
							<label for="hometown" class="col-md-3 control-label">故乡</label>
							<div class="col-md-9">
						      <c:choose>
				              	<c:when test="${userInfo == null }">
				                	<input id="hometown" name="hometown" type="text" class="form-control">
				                </c:when>
				                <c:otherwise>
				                	<input id="hometown" name="hometown" type="text" class="form-control" value="${userInfo.hometown }">
				                </c:otherwise>
				              </c:choose>
							</div>
						</div>
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<input id="btn-signup" type="submit" class="btn btn-info" value="提交">
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
<script>
$(document).ready(function(e){
	$('#birthday').datetimepicker({
		lang:'en',
		timepicker: false,
		format: 'Y-m-d'
	});
});
</script>
</body>
</html>