<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<link rel="stylesheet" type="text/css" href="css/grouphome.css" />
<c:if test="${founder.userId != sessionScope.user.userId }">
<script>
$(document).ready(function(){
	var link = "group/${group.groupId}/is_joined";
	var quit = "group/${group.groupId}/quit";
	var join = "group/${group.groupId}/join";
	$.post(link, {}, function(result){
		if (result == true){
			$("#joinLink").attr("href", quit);
			$("#joinLink").text("退出");
		}
		else{
			$("#joinLink").attr("href", join);
		}
	});
	$("#joinLink").click(function(e){
		var joinLink = $("#joinLink").attr("href");
		if (joinLink != "#"){
			$.post(joinLink, {}, function(result){
				$("#joinLink").attr("href", joinLink == join? quit : join);
				$("#joinLink").text(joinLink == join ? "退出":"＋加入小组");
			});
		}
		e.preventDefault();
	});
	
});
</script>
</c:if>
<title>${group.name }</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="">
					<h3 style="display:inline">${group.name }</h3>&nbsp;&nbsp;
					<c:if test="${founder.userId != sessionScope.user.userId }">
						<a id="joinLink" href="#" style="display:inline-block; margin-top:-7px " class="btn btn-xs btn-info"><span>+加入小组</span></a>
					</c:if>
				</div>
				<div class="jumbotron" style="margin-top:20px">
					<div id = "intro">${group.intro }</div>
					<p>欢迎加入小组</p>
				</div>
				<div class="">
					<div>
						<h4>${group.name }的话题</h4>
					</div>
					<c:choose>
						<c:when test="${fn:length(topicList) != 0 }">
							<table class="table">
								<thead>
									<tr>
										<td>标题</td>
										<td>回应</td>
										<td>时间</td>
										<td>作者</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="topic" items="${topicList }">
										<tr>
											<td><a href="topic/${topic.topicId }">${topic.title }</a></td>
											<td>${topic.cmtCount }</td>
											<td>${topic.lastCmtTime }</td>
											<td><a href="user/${topic.userId }">${topic.nickname }</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							暂无话题
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-md-4" style="padding-top:40px">
				<div>
					<h5>创建者</h5>
				</div>
				<div class="well memberlist">
					<ul>
						<li>
							<div class="pic">
								<img alt="头像" src="${founder.avatar }">
							</div>
							<div class="nickname">
								<a href="user/${founder.userId }">${founder.nickname }</a>
							</div>
						</li>
					</ul>
				</div>
				<div>
					<h5>最新加入成员</h5>
				</div>
				<div class="well memberlist">
					<ul>
						<c:forEach var="cur" items="${recentUserList }">
							<%-- <c:forEach var="x" begin="0" end="10"> --%>
							<li>
								<div class="pic">
									<img alt="头像" src="${cur.avatar }">
								</div>
								<div class="nickname">
									<a href="user/${cur.userId }">${cur.nickname }</a>
								</div>
							</li>
							<%-- </c:forEach> --%>
						</c:forEach>
					</ul>
				</div>
				<div>
					<a href="group/${group.groupId}/members" class="">浏览小组所有成员(${userCount })</a>
				</div>
				<br>
				<div>
				    <a href="group/${group.groupId}/new_topic" class="">发表话题</a>
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