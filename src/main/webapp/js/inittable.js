$(document).ready(function(e){
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
});