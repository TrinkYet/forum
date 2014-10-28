<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>
<title>我的小组话题</title>
</head>
<body>
	<div class="container text-center">
		<div class="panel-primary col-sm-9">
			<div class="col-sm-2"></div>
			<div class="panel-heading col-sm-10">
				<h3 class="panel-title">创建小组</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role = "form" action="" method="post">
					<div class="form-group">
						<label for="groupname" class="control-label col-sm-3">小组名</label>
						<div class="col-sm-9">
							<input type="text" id="groupname" name="name" class="form-control" placeholder="小组名">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-sm-3">小组简介</label>
						<div class="col-sm-9">
							<textarea rows="3" id="description" name = "intro" class="form-control" placeholder="小组简介"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="grouptype" class="control-label col-sm-3">小组类别</label>
						<div class="col-sm-9">
							<select class="form-control" id="grouptype" name="category">
								<option value="电影">电影</option>
								<option value="美食">美食</option>
								<option value="摄影">摄影</option>
								<option value="科技">科技</option>
								<option value="娱乐">娱乐</option>
								<option value="读书">读书</option>
								<option value="交易">交易</option>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>