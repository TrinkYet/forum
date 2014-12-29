$(document).ready(function(e){
	$.get("msg/topic", {}, function(result){
		var msgs = eval(result);
		if(msgs.length > 0){
			var len = msgs.length;
			$("#msgreminder").append("<span class='badge'>"+len+"</span>");
			var msgcontainer = $("#msgcontainer");
			for (var i = 0; i < len; ++i){
				msgcontainer.append("<li>"+msgs[i].nickname+"发表了<a class='msgtopiclink' href='topic/"+msgs[i].topicId+"'>新的话题</a></li>");
			}
		}
	});
	
	$("#msgcontainer").delegate("li a.msgtopiclink", "click", function(e){
		var topicId = parseInt($(this).attr("href").split("/")[1]);
		$.post("msg/topic/read", {topicId:topicId}, function(result){});
	});
});