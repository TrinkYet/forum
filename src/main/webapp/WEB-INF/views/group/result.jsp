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
			</ul>
			<c:choose>
				<c:when test="${fn:contains(cat, 'topic') }">
					<div class="col-md-8" style="min-height:500px">
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
				<c:otherwise>
					<div class="col-md-8" style="min-height:500px">
						<table class="table table-striped">
							<tbody>
								<c:forEach var = "group" items="${result }">
									<tr><td><a href="group/${group.groupId }">${group.groupName }</a></td>
									    <td>${group.mbrCount }</td>
									    <td>${topic.intro }</td>
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
		});
	</script>
</body>
</html>