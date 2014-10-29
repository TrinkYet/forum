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
	var link = "group/can_join/${group.id}";
	$.post(link, {}, function(result){
		if (result == true){
			$("#joinLink").attr("href", "group/join/${group.id}");
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
					<h2>${group.intro }</h2>
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
							<tr><td>毛位置</td><td>1000</td><td>3分钟前</td><td>wizard</td></tr>
							<tr><td>毛位置</td><td>1000</td><td>3分钟前</td><td>wizard</td></tr>
							<tr><td>毛位置</td><td>1000</td><td>3分钟前</td><td>wizard</td></tr>
							<tr><td>毛位置</td><td>1000</td><td>3分钟前</td><td>wizard</td></tr>
							<tr><td>毛位置</td><td>1000</td><td>3分钟前</td><td>wizard</td></tr>
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
			</div>
		</div>
	</div>
</body>
</html>