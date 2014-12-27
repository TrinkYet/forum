$(document).ready(function(){
		
		$("#form").validate({
			rules:{
				nickname:{
					required:true,
					maxlength:50,
					minlength:1
				},
				"email":{
					required:true,
					remote:{
						url:"user/hasregistered",
						type: "post",
						dataType: "json",
						data:{
							'email': function(){
								return $("#account").val();
							}
						}
					}
				},
				password:{
					required:true,
					maxlength:50,
					minlength:3
				},
				password2:{
					required:true,
					maxlength:50,
					minlength:3,
					equalTo: "#passwd"
				}
			},
			messages:{
				"email":{
					remote:"该邮箱已经被注册！"
				}
			},
			errorPlacement:
				function(error, element){
					error.appendTo(element.parent());
					error.css("color", "red");
					error.css("padding-top", "5px");
			    }
		});
	});