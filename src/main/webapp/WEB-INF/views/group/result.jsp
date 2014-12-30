<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/dataTable.bootstrap.css">
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/datatable.bootstrap.js"></script>
<title>搜索结果页面</title>
</head>
<body>
	<div class="container">
		<div>
			<h2>
				<c:choose>
					<c:when test="${fn:contains(cat, 'topic') }">
						话题
					</c:when>
					<c:when test="${fn:contains(cat, 'user') }">
						用户
					</c:when>
					<c:otherwise>
						小组
					</c:otherwise>
				</c:choose>
				搜索：${q }<span class="text-muted">(${fn:length(result) })</span>
			</h2>
		</div>
		<div class="row">
			<ul class="nav nav-pills" role="tablist">
			  <li role="presentation" id="topic"><a href="group/search?cat=topic&q=${q }">话题</a></li>
			  <li role="presentation" id="group"><a href="group/search?cat=group&q=${q }">小组</a></li>
			  <li role="presentation" id="user"><a href="group/search?cat=user&q=${q }">用户</a>
			</ul>
			<c:choose>
				<c:when test="${fn:contains(cat, 'topic') }">
					<div class="col-md-8" style="min-height:500px">
						<table class="table table-hover dataTable">
							<thead>
								<tr>
									<td>标题</td>
									<td>回应</td>
									<td>时间</td>
									<td>小组</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var = "topic" items="${result }">
									<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
									    <td>${topic.cmtCount }</td>
									    <td>${topic.publishTime }</td>
									    <td><a href = "group/${topic.groupId }">${topic.groupName }</a></td></tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${fn:contains(cat, 'user') }">
					<div class="col-md-8" style="min-height:500px">
						<table id="allusertable" class="table table-hover dataTable">
							<thead class="text-muted">
								<tr>
									<td>用户头像</td>
									<td>用户名</td>
									<td>账号</td>
									<td>注册时间</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cur" items="${result }">
									<tr class="usertr">
										<td>
											<div class="pic">
												<img alt="头像" src="${cur.avatar }">
											</div>
										</td>
										<td><a href="user/${cur.userId }">${cur.nickname }</a></td>
										<td class="text-muted">${cur.email }</td>
										<td class="text-muted">${cur.registerTime }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-8" style="min-height:500px">
						<table class="table table-hover dataTable">
							<thead>
								<tr><td></td><td></td><td></td><td></td></tr>
							</thead>
							<tbody>
								<c:forEach var = "group" items="${result }">
									<tr><td><a href="group/${group.groupId }">${group.name }</a></td>
									    <td>${group.mbrCount }成员</td>
									    <td>${group.createTime }</td>
									    <td><a href = "#">${group.category }</a></td></tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script>
		$(document).ready(function(e){
			$("#${cat }").addClass("active");
			$(".dataTable").DataTable({
				  "language": {
					    "lengthMenu": '<p class="text-muted">每页显示 <select>'+
					      '<option value="10">10</option>'+
					      '<option value="20">20</option>'+
					      '<option value="30">30</option>'+
					      '<option value="40">40</option>'+
					      '<option value="50">50</option>'+
					      '<option value="100">100</option>'+
					      '</select> 条记录</p>',
					      "info": "<p class='text-muted'>第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条</p>",
					      "search": "<span class='text-muted'>搜索：</span>"
				  	}
			});
		});
	</script>
</body>
</html>