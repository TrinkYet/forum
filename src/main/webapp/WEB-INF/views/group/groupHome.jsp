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
<script>
$(document).ready(function(){
	var link = "group/can_join/${group.groupId}";
	$.post(link, {}, function(result){
		if (result == true){
			$("#joinLink").attr("href", "group/join/${group.groupId}");
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
<title>XXX小组的主页</title>
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
								<tr><td>${topic.title }</td><td>${topic.cmtCount }</td><td>${topic.lastCmtTime }</td><td>${topic.nickname }</td></tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-md-4">
				<div class="text-center bg-info">
					<h3>最新加入成员</h3>
				</div>
				<div class="container">
					<ul>
						<li>user1</li>
						<li>user2</li>
						<li>user3</li>
						<li>user4</li>
						<li>user5</li>
					</ul>
				</div>
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