<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="user" value="${sessionScope.user }" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<link rel = "stylesheet" type="text/css" href = "css/userall.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.nickname}</title>
</head>
<body>
	<div class="container bodybg">
		<div class="page-header text-center clearfix">
			<div class="col-md-4 bg-info">
				<div><img src="${user.avatar }" style="width:70px; height:70px"></div>
				<div><h3>${user.nickname }</h3></div>
				<div>
					<ul class="list-inline infoul">
						<li>
							<h4><strong>${fn:length(followeeList) }</strong></h4>
							<span class="text-muted">关注</span>
						</li>
						<li>
							<h4><strong>${fn:length(followerList) }</strong></h4>
							<span class="text-muted">粉丝</span>
						</li>
						<li>
							<h4><strong>55</strong></h4>
							<span class="text-muted">话题</span>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-8"><a href="user/avatar" class="btn btn-success">上传头像</a><div></div></div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">个人信息</h3>
					</div>
					  <ul class="list-group">
					    <li class="list-group-item">${userInfo.gender }</li>
					    <li class="list-group-item">${userInfo.birthday }</li>
					    <li class="list-group-item">${userInfo.residence }</li>
					    <li class="list-group-item">${userInfo.hometown }</li>
					  </ul>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">关注的小组</h3>
					</div>
					<div class="panel-body">
						<ul class="list-unstyled">
							<c:forEach var = "team" items="${groupList }">
								<li><a href="group/${team.groupId}">${team.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">关注的人</h3>
					</div>
					<div class="panel-body">
						<ul class="list-unstyled">
							<c:forEach var = "followee" items="${followeeList }">
								<li><a href="user/${followee.userId }">${followee.nickname }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">谁关注我</h3>
					</div>
					<div class="panel-body">
						<ul class="list-unstyled">
							<c:forEach var = "follower" items="${followerList }">
								<li><a href="user/${follower.userId }">${follower.nickname }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<form action="#" method="post" role="form">
				<div class="form-group has-success">
					<label for = "comment" class="control-label">留言板~~~</label>
					<textarea id = "comment" name = "comment" class="form-control" rows="3"></textarea>
					<input type="submit" class="btn btn-info" value="留言">
				</div>
			</form>
		</div>		
		
	</div>


</body>
</html>