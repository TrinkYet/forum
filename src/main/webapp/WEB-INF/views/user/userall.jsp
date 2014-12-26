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
<link rel = "stylesheet" type="text/css" href = "css/userall.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageuser.nickname}</title>
</head>
<c:choose>
	<c:when test="${pageuser.userId != sessionScope.user.userId }">
	<script>
		$(document).ready(function(){
			var link = "user/${pageuser.userId}/is_followed";
			var cancel = "user/${pageuser.userId}/cancel_follow";
			var follow = "user/${pageuser.userId}/follow";
			$.post(link, {}, function(result){
				if (result == true){
					$("#followLink").attr("href", cancel);
					$("#followLink").text("取消关注");
				}
				else{
					$("#followLink").attr("href", follow);
				}
			});
			$("#followLink").click(function(e){
				var followLink = $("#followLink").attr("href");
				if (followLink != "#"){
					$.post(followLink, {}, function(result){
						$("#followLink").attr("href", followLink == follow? cancel : follow);
						$("#followLink").text(followLink == follow ? "取消关注":"关注");
					});
				}
				e.preventDefault();
			});
			
		});
		</script>
	</c:when>
</c:choose>
<body>
	<div class="container bodybg">
		<div class="page-header text-center clearfix">
			<div class="col-md-4 bg-info">
				<div><img src="${pageuser.avatar }" style="width:70px; height:70px"></div>
				<div>
					<h3>${pageuser.nickname }</h3>
					<c:if test="${pageuser.userId != sessionScope.user.userId }">
						<a id="followLink" class="btn btn-info" href="#">关注此人</a>
					</c:if>
				</div>
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
							<h4><strong>${fn:length(topicList) }</strong></h4>
							<span class="text-muted">话题</span>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-info">
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
			<!-- <div class="col-md-8"><a href="user/userinfo" class="btn btn-success">修改信息</a><a href="user/avatar" class="btn btn-success">上传头像</a><div></div></div> -->
		</div>
		<div class="row">
			<div class="col-md-8 bg-info" style="min-height:500px">
				<table class="table table-striped">
					<thead>
						<tr>
							<td>标题</td>
							<td>回应</td>
							<td>时间</td>
							<td>小组</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var = "topic" items="${topicList }">
							<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
							    <td>${topic.cmtCount }</td>
							    <td>${topic.lastCmtTime }</td>
							    <td><a href = "group/${topic.groupId }">${topic.groupName }</a></td></tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-4">
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