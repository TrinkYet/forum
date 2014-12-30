$(document).ready(function(e){
	
	$("#msgform").submit(function(e){
		var href = $(this).attr("action");
		$.post(href, $(this).serialize(), function(result){
			$('#deletemessage').text(result);
			$('#messageModal').modal('show');
		});
		e.preventDefault();
	});
	
	
	$("a[href *= '#']").each(function(e){
				var href = $(this).attr('href');
				$(this).attr("href", window.location.pathname+href);
			});
	
	$("#msgsingleform").submit(function(e){
		$.post($(this).attr("action"), $(this).serialize(), function(result){
			$("#sendmsgModal").modal('hide');
		});
		e.preventDefault();
	});
	
	$("a.sendmsgbtn").click(function(e){
		var href = $(this).attr("href");
		$("#msgsingleform").attr("action", href);
		$("#sendmsgModal").modal('show');
		e.preventDefault();
	});
	
	$(".dataTable").DataTable({
		  "language": {
			    "lengthMenu": '<p class="text-muted">每页显示 <select>'+
			      '<option value="10">10</option>'+
			      '<option value="20">20</option>'+
			      '<option value="30">30</option>'+
			      '<option value="40">40</option>'+
			      '<option value="50">50</option>'+
			      '<option value="100">100</option>'+
			      '</select> 条记录</p>',
			      "info": "<p class='text-muted'>第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条</p>",
			      "search": "<span class='text-muted'>搜索：</span>"
		  	}
	});
	
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
	
	$(".usertr").each(function(e){
		var status = $(this).children(":nth(4)").children(":nth(0)");
		var option = $(this).children(":nth(5)").children(":nth(0)").children(":nth(0)");
		if(status.text() == "forbidden"){
			option.addClass('fa-unlock');
			var href = option.parent().attr("href");
			option.parent().attr("href", "admin/unforbid/" + href.split("/")[2]);
			status.addClass('red-bg');
		}
		else{
			option.addClass('fa-ban');
			if (status.text() == "activated"){
				status.addClass('label-success');
			}
			else{
				status.addClass('yellow-bg');
			}
		}
	});
	
	
	
	
	$('[data-toggle=collapse]').click(function(){
	  	// toggle icon
	  	$(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
	});
	$.get("admin/count/user", {}, function(result){
		$("#usercount").text(result);
		$("#usercount").attr("data-to", result);
		var num = parseInt(result);
		$("#usercount").attr("data-speed", Math.ceil(num/10));
		$("#usercount").countTo();
	});
	$.get("admin/count/group", {}, function(result){
		$("#groupcount").text(result);
		$("#groupcount").attr("data-to", result);
		var num = parseInt(result);
		$("#groupcount").attr("data-speed", Math.ceil(num/10));
		$("#groupcount").countTo();
	});
	$.get("admin/count/topic", {}, function(result){
		$("#topiccount").text(result);
		$("#topiccount").attr("data-to", result);
		var num = parseInt(result);
		$("#topiccount").attr("data-speed", Math.ceil(num/10));
		$("#topiccount").countTo();
	});
	$.get("admin/count/comment", {}, function(result){
		$("#commentcount").text(result);
		$("#commentcount").attr("data-to", result);
		var num = parseInt(result);
		$("#commentcount").attr("data-speed", Math.ceil(num/10));
		$("#commentcount").countTo();
	});
});