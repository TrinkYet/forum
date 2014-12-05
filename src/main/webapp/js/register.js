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
								console.log("user/hasregistered?email="+$("#account").val());
								return $("#account").val();
							}
						}
					}
				},
				password:{
					required:true,
					maxlength:50,
					minlength:3
				}
			},
			messages:{
				"email":{
					remote:"该邮箱已经被注册！"
				}
			}
		});
	});