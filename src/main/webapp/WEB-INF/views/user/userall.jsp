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
<link rel="stylesheet" type="text/css" href="css/grouphome.css" />
<link rel="stylesheet" type="text/css" href="css/dataTable.bootstrap.css">
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/datatable.bootstrap.js"></script>
<script src="js/inittable.js"></script>
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
		<div class="row">
			<div class="col-md-8" style="min-height:500px;padding-top:50px">
				<h3 class="text-muted">发表的话题</h3>
				<table class="table dataTable">
					<thead class="text-muted">
						<tr>
							<td>标题</td>
							<td>回应</td>
							<td>时间</td>
							<td>小组</td>
							<c:if test="${pageuser.userId == sessionScope.user.userId }">
							<td>操作</td>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var = "topic" items="${topicList }">
							<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
							    <td>${topic.cmtCount }</td>
							    <td>${topic.lastCmtTime }</td>
							    <td><a href = "group/${topic.groupId }">${topic.groupName }</a></td>
							    <c:if test="${pageuser.userId == sessionScope.user.userId }">
							    <td>
							    	<a href="topic/${topic.topicId }/modify">修改</a>/
							    	<a href="topic/${topic.topicId }/delete"  class="deletebutton" data-toggle="modal" data-target="#deleteModal">删除</a>
							    </td>
							    </c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-4" style="padding-top:50px">
				<div class=" text-center" style="background: #fff4e8;padding-top:10px; padding-bottom:10px; margin-bottom:10px">
				<div>
					<img src="${pageuser.avatar }" style="height: 170px; border-radius:5px">
				</div>
				<div>
					<h3 class="text-muted">${pageuser.nickname }</h3>
					<c:if test="${pageuser.userId != sessionScope.user.userId }">
						<a id="followLink" class="btn btn-info" href="#">关注此人</a>
					</c:if>
				</div>
				<div style="margin-top:10px;margin-bottom:10px">
					常居：${userInfo.residence }
				</div>
				<div class="text-primary">
					<ul class="list-inline infoul">
						<li>
							<h4>
								<strong>${fn:length(followeeList) }</strong>
							</h4> <span class="text-muted">关注</span>
						</li>
						<li>
							<h4>
								<strong>${fn:length(followerList) }</strong>
							</h4> <span class="text-muted">粉丝</span>
						</li>
						<li>
							<h4>
								<strong>${fn:length(topicList) }</strong>
							</h4> <span class="text-muted">话题</span>
						</li>
					</ul>
				</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">创建的小组</h3>
					</div>
					<div class="panel-body">
						<ul class="list-unstyled">
							<c:forEach var = "team" items="${createdList }">
								<li>
									<div class="col-md-6"><a href="group/${team.groupId}">${team.name }</a></div>
									<c:if test="${pageuser.userId == sessionScope.user.userId }">
									<div class="col-md-6">
										<a href="group/${team.groupId }/modify">修改</a>
										<a href="group/${team.groupId }/delete" class="deletebutton" >删除</a>
									</div>
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
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
					<div class="panel-body memberlist">
						<ul class="list-unstyled">
							<c:forEach var = "followee" items="${followeeList }">
								<li>
									<div class="pic">
										<img alt="头像" src="${followee.avatar }">
									</div>
									<div class="nickname">
										<a href="user/${followee.userId }">${followee.nickname }</a>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">谁关注我</h3>
					</div>
					<div class="panel-body memberlist">
						<ul class="list-unstyled">
							<c:forEach var = "follower" items="${followerList }">
								<li>
									<div class="pic">
										<img alt="头像" src="${follower.avatar }">
									</div>
									<div class="nickname">
										<a href="user/${follower.userId }">${follower.nickname }</a>
									</div>
								</li>
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
	<c:if test="${pageuser.userId == sessionScope.user.userId }">
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Warning</h4>
				</div>
				<div class="modal-body">
					<p>确定要删除吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="confirmdelete" type="button" class="btn btn-primary">确认</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p id="deletemessage"></p>
				</div>
				<div class="modal-footer">
					<a href="user/${sessionScope.user.userId }" type="button" class="btn btn-primary">确认</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function(e){
		$(".deletebutton").click(function(e){
			e.preventDefault();
			$("#confirmdelete").attr("href", $(this).attr("href"));
			$("#deleteModal").modal('show');
			return false;
		});
		
		$("#confirmdelete").click(function(e){
			var link = $(this).attr("href");
			$.post(link, {}, function(result){
				$('#deleteModal').modal('hide');
				$('#deletemessage').text(result);
				$('#messageModal').modal('show');
			});
		});
		
	});
	</script>
	</c:if>
</body>
</html>