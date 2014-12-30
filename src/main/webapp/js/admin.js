$(document).ready(function(e){
	$("a[href='#']").attr("href", window.location.pathname+"#");
	$('[data-toggle=collapse]').click(function(){
	  	// toggle icon
	  	$(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
	});
});