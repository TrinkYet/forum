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
		<div id="rawintro" class="hidden">${group.intro }</div>
		<div class="col-md-8 clearfix">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">修改小组</h3>
			</div>
			<div class="panel-body">
				<form role = "form" action="" method="post">
					<div class="form-group">
						<label for="groupname" class="control-label">小组名</label>
						<div class="">
							<input type="text" id="groupname" name="name" value="${group.name }" class="form-control" placeholder="小组名">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label">小组简介</label>
						<div class="">
							<!-- <textarea rows="3" id="description" name = "intro" class="form-control" placeholder="小组简介"></textarea> -->
							<script id = "description" name = "intro">
							</script>
						</div>
					</div>
					<div class="form-group">
						<label for="grouptype" class="control-label">小组类别</label>
						<div class="text-center">
							<div class="">
							<select class="form-control" id="grouptype" name="category" style="margin-left:35%;width:30%">
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
					</div>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
			</div>
		</div>
		</div>
	</div>
	
	<script type="text/javascript" src="editor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="editor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var editor = UE.getEditor('description');
        editor.addListener('ready', function(e){
        	this.setContent($("#rawintro").html());
        });
        $(document).ready(function(e){
        	//$("#grouptype option[value='${group.category}']").click();
        	$("#grouptype").val('${group.category}');
        });
    </script>
</body>
</html>