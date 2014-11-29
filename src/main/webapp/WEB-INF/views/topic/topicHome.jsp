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
<link type="text/css" rel = "stylesheet" href="css/topic.css">
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
						<h3 class="media-heading">${topic.nickname}的话题</h3>
					</div>
				</div>
				<div class="container">
					<div class="col-md-7">
						<h4><p class="text-primary">${topic.title }</p></h4>
						<span class="text-muted">${topic.publishTime }</span>
						<div class="well" id = "content">
							${topic.content }
						</div>
						<div>
						<ul id = "commentlist" class="media-list">
						  <c:forEach var = "keyvalue" items="${cmtMap }">
							  <c:set var = "comment" value="${keyvalue.value }"></c:set>
							  <li class="media">
							    <a class="media-left" href="#">
							      <img src="images/user_normal.jpg" alt="...">
							    </a>
							    <div class="media-body">
							      <h4 class="media-heading">${comment.userId }&nbsp;${comment.commentTime }<p class="pull-right respond" ref="${comment.commentId }"><a href="#commentform">回应</a></p></h4>
							      <blockquote>${cmtMap[comment.toCommentId].text }</blockquote>
							      <p>${comment.text }</p>
							    </div>
							  </li>
						  </c:forEach>
						</ul>
						</div>
						<div>
							<input id = "tocommentid" type="hidden" value="">
							<form id="commentform" action="topic/${topic.topicId }/post_comment" role="form" method="post">
								<textarea name="text" rows="3" class = "form-control"></textarea>
								<input id="submitcomment" type="submit" value="回应" class="btn btn-info">
							</form>
						</div>
					</div>
					
				</div>
			</div>	
			</div>
			<div class="col-md-3">
			
			</div>
		</div>
	</div>
<script src="editor/ueditor.parse.js"></script>
<script>
   uParse('#content',{
	   rootPath : 'editor'
   });
   $(document).ready(function(){
	   $("p.respond a").click(function(e){
		  var toId = $(this).parent().attr("ref");
		  $("#tocommentid").val(toId);
		  e.preventDefault(); 
	   });
	   $("#submitcomment").click(function(e){
		   $.post($("#commentform").attr("action"),
				   {userId:${sessionScope.user.userId }, 
			        text:$("textarea[name='text']").val(),
			        toCommentId:$("#tocommentid").val()==""?0:long($("#tocommentid").val())}, 
			        function(result){
			        	if(result){
			        		$("#commentlist").append("<li class='media'>"+
			     				   "<a class='media-left' href='#'>"+
			    				   "<img src='images/user_normal.jpg' alt='...'>"+
			    		           "</a>"+
			    		    	   "<div class='media-body'>"+
			    		           "<h4 class='media-heading'>"+result.userId+"&nbsp;"+"<p class='pull-right respond' ref='"+result.commentId+"'><a href='#commentform'>回应</a></p></h4>"+
			    		           "<p>"+result.text+"</p>"+
			    		           "</div></li>");
			        	}
			        });
		   $("textearea[name='text']").val("");
		   e.preventDefault();
	   });
   });
</script>
</body>
</html>