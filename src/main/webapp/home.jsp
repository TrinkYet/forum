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
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/adminhome.css">
<script src="js/jquery.countTo.js"></script>
<title>小组网站管理员</title>

<script src="js/admin.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-2">
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
						<li role="presentation" class="active">
							<a href="#home" data-toggle="tab"><i class="glyphicon glyphicon-home"></i> 全站概览</a>
						</li>
						<li><a href="#grouplist" data-toggle="tab"><i class="glyphicon glyphicon-cog"></i>
								小组</a></li>
						<li><a href="#messagepanel" data-toggle="tab"><i class="glyphicon glyphicon-comment"></i>
								系统消息</a></li>
						<li><a href="#userlist" data-toggle="tab"><i class="glyphicon glyphicon-user"></i>
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
		<div class="col-sm-10">
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">
					<div class="row">
						<div class="col-lg-3 col-sm-6 col-xs-12">
							<div class="main-box infographic-box">
								<i class="fa fa-user red-bg"></i> <span class="headline">用户</span>
								<span class="value"> 
								<span id="usercount" class="timer" data-from="0"
									data-to="0" data-speed="10" data-refresh-interval="50">0</span>
								</span>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6 col-xs-12">
							<div class="main-box infographic-box">
								<i class="fa fa-group emerald-bg"></i>
								<span class="headline">小组</span>
								<span class="value">
								<span id="groupcount" class="timer" data-from="0" data-to="0" data-speed="800" data-refresh-interval="30">0</span>
								</span>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6 col-xs-12">
							<div class="main-box infographic-box">
								<i class="fa fa-comment green-bg"></i>
								<span class="headline">话题</span>
								<span class="value">
								<span id="topiccount" class="timer" data-from="0" data-to="0" data-speed="900" data-refresh-interval="60">0</span>
								</span>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6 col-xs-12">
							<div class="main-box infographic-box">
								<i class="fa fa-envelope yellow-bg"></i>
								<span class="headline">评论</span>
								<span class="value">
								<span id="commentcount" class="timer" data-from="0" data-to="0" data-speed="1100">0</span>
								</span>
							</div>
						</div>
					</div>
					<div>
						<%-- <div class=""><h3>小组话题</h3></div>
						<div>
							<table class="table table-hover">
								<thead class="text-muted">
									<tr>
										<td>标题</td>
										<td>回应</td>
										<td>时间</td>
										<td>小组</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var = "topic" items="${groupTopicList }">
										<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
										    <td class="text-muted">${topic.cmtCount }</td>
										    <td class="text-muted">${topic.lastCmtTime }</td>
										    <td><a href="group/${topic.groupId}">${topic.groupName }</a></td></tr>
									</c:forEach>
								</tbody>
							</table>
						</div> --%>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="messagepanel">messagepanel</div>
				<div role="tabpanel" class="tab-pane" id="userlist">userlist</div>
				<div role="tabpanel" class="tab-pane" id="grouplist">grouplist</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>