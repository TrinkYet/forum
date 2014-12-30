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
							  <div class="media" id="cmt${comment.commentId }">
							    <a class="media-left" href="user/${comment.userId }">
							      <img class="media-object" style="width:48px;height:48px" src="avatar/user/${comment.userId }.jpg" alt="...">
							    </a>
							    <div class="media-body container">
							      <p class="bg-info media-heading">${comment.nickname }&nbsp;&nbsp;<span class="text-muted">${comment.commentTime }</span><span class="pull-right respond" ref="${comment.commentId }">
							      	<c:choose>
							      		<c:when test = "${sessionScope.user.status == 'admin' }">
							      			<a href="admin/del/comment/${comment.commentId }" class="deletebutton">删除</a>
							      		</c:when>
							      		<c:otherwise>
							      			<a href="#commentform">回应</a>
							      		</c:otherwise>
							      	</c:choose>
							      	</span>
							      </p>
							      <c:if test="${comment.toCommentId != 0 and cmtMap[comment.toCommentId] != null }">
							      	<c:set var = "tocomment" value="${cmtMap[comment.toCommentId] }"></c:set>
							      	<blockquote>
							      		<p class="bg-info media-heading">${tocomment.nickname }&nbsp;&nbsp;<span class="text-muted">${tocomment.commentTime }</span><span class="pull-right respond" ref="${tocomment.commentId }">
							      		<c:choose>
							      		<c:when test = "${sessionScope.user.status != 'admin' }">
							      			<a href="#commentform">回应</a>
							      		</c:when>
							      		</c:choose></span></p>   		
							      		<p>${tocomment.text }</p>
							      	</blockquote>
							      </c:if>
							      <p>${comment.text }</p>
							    </div>
							  </div>
						  </c:forEach>
						</div>
						<c:choose>
						<c:when test = "${sessionScope.user.status != 'admin' }">
							<div>
								<input id = "tocommentid" type="hidden" value="">
								<form id="commentform" action="topic/${topic.topicId }/post_comment" role="form" method="post">
									<textarea name="text" rows="3" class = "form-control"></textarea>
									<input id="submitcomment" type="submit" value="回应" class="btn btn-info">
								</form>
							</div>
						</c:when>
						<c:otherwise>
						<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title">Warning</h4>
									</div>
									<div class="modal-body">
										<p>确定要执行操作吗？</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button id="confirmdelete" type="button" class="btn btn-primary">确认</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title">Message</h4>
									</div>
									<div class="modal-body">
										<p id="deletemessage"></p>
									</div>
									<div class="modal-footer">
										<a href="topic/${topic.topicId }" type="button" class="btn btn-primary">确认</a>
									</div>
								</div>
							</div>
						</div>
						<script>
							$(document).ready(function(e){
								$(".deletebutton").click(function(e){
									e.preventDefault();
									$("#confirmdelete").attr("href", $(this).attr("href"));
									$('#deleteModal').modal('show');
									return false;
								});
								
								$("#confirmdelete").click(function(e){
									var link = $(this).attr("href");
									$.post(link, {}, function(result){
										$('#deleteModal').modal('hide');
										$('#deletemessage').text(result);
										$('#messageModal').modal('show');
									});
								});
								
							});
						</script>
						</c:otherwise>
						</c:choose>
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
			    				   "<img class='media-object' src='${sessionScope.user.avatar }' style='width:48px;height:48px' alt='...'>"+
			    		           "</a>"+
			    		    	   "<div class='media-body container'>"+
			    		           "<p class='media-heading bg-info'>"+"${sessionScope.user.nickname }"+"&nbsp;&nbsp;<span class='text-muted'>"+formatDate(new Date($.now()))+"</span>"+
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