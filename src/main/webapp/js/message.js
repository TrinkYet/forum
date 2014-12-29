$(document).ready(function(e){
	var msgcontainer = $("#msgcontainer");
	var msgnumber = 0;
	$.get("msg/topic", {}, function(result){
		var msgs = eval(result);
		if(msgs.length > 0){
			var len = msgs.length;
			msgnumber += len;
			for (var i = 0; i < len; ++i){
				msgcontainer.append("<p>"+msgs[i].nickname+"发表了<a class='msgtopiclink' href='topic/"+msgs[i].topicId+"'>新的话题</a></p>");
			}
			msgcontainer.append("<li class='divider'></li>");
		}
		$.get("msg/cmt", {}, function(result){
			var msgs = eval(result);
			if(msgs.length > 0){
				var len = msgs.length;
				msgnumber += len;
				for (var i = 0; i < len; ++i){
					msgcontainer.append("<p>话题"+msgs[i].title+"中有新的<a class='msgcmtlink' href='topic/"+msgs[i].topicId+"#cmt"+msgs[i].commentId+"'>回复</p>");
				}
			}
			if(msgnumber > 0)
				$("#msgreminder").append("<span class='badge'>"+msgnumber+"</span>");
		});
	});
	
	
	
	
	$("#msgcontainer").delegate("a.msgtopiclink", "click", function(e){
		var topicId = parseInt($(this).attr("href").split("/")[1]);
		$.post("msg/topic/read", {topicId:topicId}, function(result){});
	});
	
	$("#msgcontainer").delegate("a.msgcmtlink", "click", function(e){
		var cmtId = parseInt($(this).attr("href").split("#cmt")[1]);
		$.post("msg/cmt/read", {cmtId: cmtId}, function(result){});
		$(this).parent().remove();
		msgnumber -= 1;
		if(msgnumber > 0)
			$("#msgreminder>span.badge").text(msgnumber);
		else
			$("#msgreminder>span.badge").remove();
	});
	
});