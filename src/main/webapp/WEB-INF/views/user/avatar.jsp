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
    <h1>请选择上传的头像文件</h1>
    <form method="post" action="<c:url value="/user/avatar" />" enctype="multipart/form-data">
        <input type="text" name="name" />
        <input type="file" name="avatar" />
        <input type="submit" />
    </form>
</body>
</html>