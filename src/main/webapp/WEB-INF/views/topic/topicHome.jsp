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
					<div class="pull-left"><img src="${pageuser.avatar }" alt="images/user_normal.jpg" class="media-object" width="48" height="48"></div>
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
						<div id = "commentlist">
						  <c:forEach var = "keyvalue" items="${cmtMap }">
							  <c:set var = "comment" value="${keyvalue.value }"></c:set>
							  <div class="media">
							    <a class="media-left" href="user/${comment.userId }">
							      <img class="media-object" style="width:48px;height:48px" src="avatar/user/${comment.userId }.jpg" alt="...">
							    </a>
							    <div class="media-body container">
							      <p class="bg-info media-heading">${comment.nickname }&nbsp;<span class="text-muted">${comment.commentTime }</span><span class="pull-right respond" ref="${comment.commentId }"><a href="#commentform">回应</a></span></p>
							      <c:if test="${comment.toCommentId != 0 }">
							      	<c:set var = "tocomment" value="${cmtMap[comment.toCommentId] }"></c:set>
							      	<blockquote>
							      		<p class="bg-info media-heading">${tocomment.nickname }&nbsp;<span class="text-muted">${tocomment.commentTime }</span><span class="pull-right respond" ref="${tocomment.commentId }"><a href="#commentform">回应</a></span></p>   		
							      		<p>${tocomment.text }</p>
							      	</blockquote>
							      </c:if>
							      <p>${comment.text }</p>
							    </div>
							  </div>
						  </c:forEach>
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
   
   $(document).ready(function(){
	   var quote = "";
	   uParse('#content',{
		   rootPath : 'editor'
	   });
	   $("#commentlist").delegate("span.respond a","click",function(e){
		  var toId = $(this).parent().attr("ref");
		  $("#tocommentid").val(toId);
		  var header = $(this).parent().parent();
		  var content = header.parent().children().last();
		  quote = "<blockquote>"+$("<blockquote></blockquote>").append(header.clone(), content.clone()).html()+"</blockquote>";
		  $("textarea").focus();
		  e.preventDefault(); 
	   });
	   function padzero(text){
		   if(text<10){
			   return "0"+text;
		   }
		   return text;
	   }
	   function formatDate(now){   
          var year=now.getFullYear();   
          var month=padzero(now.getMonth()+1);   
          var date=padzero(now.getDate());   
          var hour=padzero(now.getHours());   
          var minute=padzero(now.getMinutes());   
          var second=padzero(now.getSeconds());   
          return year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;   
       }   
	   $("#submitcomment").click(function(e){
		   $.post($("#commentform").attr("action"),
				   {userId:${sessionScope.user.userId }, 
			        text:$("textarea[name='text']").val(),
			        toCommentId:$("#tocommentid").val()==""?0:parseInt($("#tocommentid").val())}, 
			        function(result){
			        	if(result){
			        		$("#commentlist").append("<div class='media'>"+
			     				   "<a class='media-left' href='#'>"+
			    				   "<img src='${sessionScope.user.avatar }' alt='...'>"+
			    		           "</a>"+
			    		    	   "<div class='media-body container'>"+
			    		           "<p class='media-heading bg-info'>"+${sessionScope.user.nickname }+"&nbsp;<span class='text-muted'>"+formatDate(new Date($.now()))+"</span>"+
			    		           "<span class='pull-right respond' ref='"+result.commentId+"'><a href='#commentform'>回应</a></span></p>"+
			    		           quote+
			    		           "<p>"+result.text+"</p>"+
			    		           "</div></div>");
			        	}
			        });
		   $("textarea[name='text']").val("");
		   e.preventDefault();
	   });
   });
</script>
</body>
</html>