<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小组网站管理员</title>
<style>
.list-unstyled li > ul > li {
   margin-left:10px;
   padding:8px;
}
</style>
<script src="js/admin.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-3">
			<!-- Left column -->
			<a href="#"><strong><i class="glyphicon glyphicon-wrench"></i>管理</strong></a>
			<hr>

			<ul class="list-unstyled">
				<li class="nav-header">
					<a href="#" data-toggle="collapse" data-target="#userMenu">
						<h5>
							导航 <i class="glyphicon glyphicon-chevron-down"></i>
						</h5>
					</a>
					<ul class="list-unstyled collapse in" id="userMenu">
						<li class="active"><a href="#"><i class="glyphicon glyphicon-home"></i> 全站概览</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i>
								小组</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
								系统消息</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-user"></i>
								用户</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-flag"></i>
								Transactions</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-exclamation-sign"></i> Rules</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-off"></i>
								Logout</a></li>
					</ul>
				</li>
			<hr>
		</div>
		<div class="col-sm-9">
		</div>
	</div>
</div>

</body>
</html>