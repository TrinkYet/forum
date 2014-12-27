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
<title>修改话题</title>
</head>
<body>
	<div class="container">
		<div id="rawcontent" style="display:none">${topic.content }</div>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3>修改话题</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="" method="post">
							<div class="form-group">
								<label for="title">标题</label>
								<input id="title" name="title" class="form-control" type="text" value="${topic.title }" >
							</div>
							<div class="form-group">
								<label for="content">内容</label>
								<script id = "content" name = "content"></script>
							</div>
							<button type="submit" class="btn btn-info">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="editor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="editor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    	var editor = UE.getEditor('content');
        editor.addListener('ready', function(e){
        	this.setContent($("#rawcontent").html());
        });
    </script>
</body>
</html>