<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<title>我的小组话题</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="text-center bg-info"><h3>小组话题</h3></div>
				<div>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<td>标题</td>
								<td>回应</td>
								<td>时间</td>
								<td>作者</td>
								<td>小组</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>毛位置你个狗</td>
								<td>1000</td>
								<td>3分钟前</td>
								<td>狗雷</td>
								<td>狗雷不是狗</td>
							</tr>
							<tr>
								<td>毛位置你个狗</td>
								<td>1000</td>
								<td>3分钟前</td>
								<td>狗雷</td>
								<td>狗雷不是狗</td>
							</tr>
							<tr>
								<td>毛位置你个狗</td>
								<td>1000</td>
								<td>3分钟前</td>
								<td>狗雷</td>
								<td>狗雷不是狗</td>
							</tr>
							<tr>
								<td>毛位置你个狗</td>
								<td>1000</td>
								<td>3分钟前</td>
								<td>狗雷</td>
								<td>狗雷不是狗</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-md-3">
				<div>
					<h3>我的用户名</h3>
					<a href="group/create" class="btn btn-primary">申请创建小组</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>