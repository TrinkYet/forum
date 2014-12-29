<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="user" value="${sessionScope.user }" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/navbar.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.nickname}</title>
</head>
<body>
	<div class="container">
    <h1>请选择上传的头像文件</h1>
    <div>
    	<img src="images/avatar_default.png" id="cropbox" />
    </div>
    <div id="preview-pane">
      <div class="preview-container">
        <img src="images/avatar_default.png" id ="previewimg" class="jcrop-preview" alt="Preview" />
      </div>
    </div>
   	<div>
	    <form method="post" action="<c:url value="/user/avatar" />" enctype="multipart/form-data" onsubmit="return checkCoords();">
	        <input type="hidden" id="x" name="x" />
			<input type="hidden" id="y" name="y" />
			<input type="hidden" id="w" name="w" />
			<input type="hidden" id="h" name="h" />
            <input type="file" id="inputimg" name="avatar"/>
	        <input type="submit" class="btn btn-info" value="上传"/>
	    </form>
    </div>
    </div>
<link rel="stylesheet" type="text/css" href="css/jquery.Jcrop.min.css"/>
<link rel="stylesheet" type="text/css" href="css/upload.avatar.css"/>
<script type="text/javascript" src="js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="js/upload.avatar.js"></script>

</body>
</html>