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
<title>Topic</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
			<div class="container">
				<div class="media">
					<div class="pull-left"><img src="images/user_normal.jpg" class="media-object" width="48" height="48"></div>
					<div class="media-body">
						<h3 class="media-heading">user的话题</h3>
					</div>
				</div>
				<div class="container">
					<div>
						<h4><p class="text-primary">标题</p><p>2014-10-29</p></h4>
						<div class="container">
							<p>This is the body of this topic</p>
						</div>
					</div>
					<div>
						
					</div>
				</div>
			</div>	
			</div>
			<div class="col-md-3">
			
			</div>
		</div>
	</div>
</body>
</html>