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
<title>${group.name }成员列表</title>
</head>
<body>
	<div class="container">
		<h4>${group.name }的成员 (${group.mbrCount })</h4>
		<div class="well memberlist">
					<ul>
						<c:forEach var="cur" items="${userList }">
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
	</div>
</body>
</html>