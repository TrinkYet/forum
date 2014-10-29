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
<title>发表新话题</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3>发表话题</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="" method="post">
							<div class="form-group">
								<label for="title">标题</label>
								<input id="title" name="title" class="form-control" type="text" >
							</div>
							<div class="form-group">
								<label for="text">内容</label>
								<textarea id="text" name="text" rows="10" class="form-control"></textarea>
							</div>
							<button type="submit" class="btn btn-info">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>