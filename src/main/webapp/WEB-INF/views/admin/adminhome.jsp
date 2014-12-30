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
<link rel="stylesheet" type="text/css" href="css/grouphome.css">
<link rel="stylesheet" type="text/css" href="css/dataTable.bootstrap.css">
<!-- <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"> -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/datatable.bootstrap.js"></script>
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
						<li><a href="#grouplist" data-toggle="tab"><i class="fa fa-group"></i>
								小组</a></li>
						<li><a href="#messagepanel" data-toggle="tab"><i class="glyphicon glyphicon-comment"></i>
								系统消息</a></li>
						<li><a href="#userlist" data-toggle="tab"><i class="glyphicon glyphicon-user"></i>
								用户</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-off"></i>
								登出</a></li>
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
						<div class=""><h3>所有话题</h3></div>
						<div>
							<table id="alltopictable" class="table table-hover dataTable">
								<thead class="text-muted">
									<tr>
										<td>标题</td>
										<td>回应</td>
										<td>时间</td>
										<td>小组</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var = "topic" items="${allTopic }">
										<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
										    <td class="text-muted">${topic.cmtCount }</td>
										    <td class="text-muted">${topic.publishTime }</td>
										    <td><a href="group/${topic.groupId}">${topic.groupName }</a></td>
										    <td>
										    	<a href="admin/del/topic/${topic.topicId }" class="deletebutton" data-toggle="modal" data-target="#deleteModal">
										    		<i class="fa fa-trash-o"></i>
										    	</a>
										    </td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="messagepanel">messagepanel</div>
				<div role="tabpanel" class="tab-pane" id="userlist">
					<div class=""><h3>所有用户</h3></div>
						<div>
							<table id="allusertable" class="table table-hover dataTable">
								<thead class="text-muted">
									<tr>
										<td>用户头像</td>
										<td>用户名</td>
										<td>账号</td>
										<td>注册时间</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var = "cur" items="${allUser }">
										<tr>
											<td>
												<div class="pic">
												<img alt="头像" src="${cur.avatar }">
												</div>
											</td>
											<td><a href="user/${cur.userId }">${cur.nickname }</a></td>
										    <td class="text-muted">${cur.email }</td>
										    <td class="text-muted">${cur.registerTime }</td>
										    
										    <td>封禁</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="grouplist">
					<div class=""><h3>小组列表</h3></div>
						<div>
							<table id="allgrouptable" class="table table-hover dataTable">
								<thead class="text-muted">
									<tr>
										<td>小组名</td>
										<td>类别</td>
										<td>成员数</td>
										<td>创建时间</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var = "group" items="${allGroup }">
										<tr><td><a href="group/${group.groupId }">${group.name }</a></td>
										    <td class="text-muted">${group.category }</td>
										    <td class="text-muted">${group.mbrCount }</td>
										    <td>${group.createTime }</td>
										    <td>删除</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
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
					<a href="admin/home" type="button" class="btn btn-primary">确认</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function(e){
		$(".deletebutton").click(function(e){
			$("#confirmdelete").attr("href", $(this).attr("href"));
			e.preventDefault();
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
</body>
</html>