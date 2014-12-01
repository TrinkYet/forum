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
<link rel="stylesheet" type="text/css" href="css/grouphome.css" />
<script>
$(document).ready(function(){
	var link = "group/${group.groupId}/is_joined";
	$.post(link, {}, function(result){
		if (result == "true"){
			$("#joinLink").attr("href", link);
		}
		else{
			$("#joinLink").text("已加入");
		}
	});
	$("#joinLink").click(function(e){
		var joinLink = $("#joinLink").attr("href");
		$.post(joinLink, {}, function(result){
			$("#joinLink").attr("href","#");
			$("#joinLink").text("已加入");
		});
		e.preventDefault();
	});
	
});
</script>
<title>${group.name }</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="text-center bg-primary">
					<h2>${group.name }<a id="joinLink" href="#" class="btn btn-info">+加入小组</a></h2>
				</div>
				<div class="jumbotron">
					<div id = "intro">${group.intro }</div>
					<p>欢迎加入小组</p>
				</div>
				<div class="">
					<table class="table table-striped">
						<thead>
							<tr>
								<td>标题</td>
								<td>回应</td>
								<td>时间</td>
								<td>作者</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var = "topic" items="${topicList }">
								<tr><td><a href="topic/${topic.topicId }">${topic.title }</a></td>
								    <td>${topic.cmtCount }</td>
								    <td>${topic.lastCmtTime }</td>
								    <td><a href = "user/${topic.userId }">${topic.nickname }</a></td></tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-md-4">
				<div class="text-center bg-info">
					<h5>最新加入成员</h5>
				</div>
				<div class="well memberlist">
					<ul>
						<c:forEach var="cur" items="${recentUserList }">
							<c:forEach var="x" begin="0" end="10">
							<li>
								<div class="pic">
									<img alt="头像" src="${cur.avatar }">
								</div>
								<div class="nickname">
									<a href="user/${cur.userId }">${cur.nickname }</a>
								</div>
							</li>
							</c:forEach>
						</c:forEach>
					</ul>
				</div>
				<div>
					<a href="group/${group.groupId}/members" class="btn btn-info">浏览小组所有成员(${userCount })</a>
				</div>
				<br>
				<div>
				    <a href="group/${group.groupId}/new_topic" class="btn btn-primary">发表话题</a>
				</div>
			</div>
		</div>
	</div>
<script src="editor/ueditor.parse.js"></script>
<script>
	   uParse('#intro',{
		   rootPath : 'editor'
	   });
</script>
</body>
</html>