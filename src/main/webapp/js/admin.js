$(document).ready(function(e){
	$("a[href *= '#']").each(function(e){
				var href = $(this).attr('href');
				$(this).attr("href", window.location.pathname+href);
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
			      "search": "搜索："
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