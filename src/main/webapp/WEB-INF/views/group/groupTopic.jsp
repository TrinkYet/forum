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
				<div class=""><h3>小组话题</h3></div>
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
				</div>
			</div>
			<div class="col-md-3">
				<div style="padding-top:50px">
					<a href="group/create" class="btn btn-primary">申请创建小组</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>