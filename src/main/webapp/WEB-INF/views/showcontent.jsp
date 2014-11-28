<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="editor/ueditor.parse.js"></script>
<title>show content</title>
</head>
<body>
	<div id = "content">
		${content }
	</div>
	<script>
	   uParse('#content',{
		   rootPath : 'editor'
	   });
	</script>
</body>
</html>