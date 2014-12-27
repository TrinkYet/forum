<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../favicon.ico"> -->

    <title>Signin for Gu's Forum</title>

    <%@ include file="/include/header.jsp"%>

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <%-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> --%>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
      <s:url var="authUrl" 
          value="/static/j_spring_security_check" />
      <%-- <form id="loginform" class="form-signin" role="form" method="post" action="${authUrl}">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input name="j_username" type="email" class="form-control" placeholder="Email address" required autofocus>
        <input name="j_password" type="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input name="_spring_security_remember_me" type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form> --%>

		<div id="loginbox" style="margin-top:50px" class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
					<div style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12">
					
					</div>

					<form id="loginform" class="form-horizontal" role="form" method="post" action="${authUrl}">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
						    <input id="login-username" type="email" class="form-control" name="j_username" placeholder="email">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-lock"></i>
							</span> 
							<input id="login-password" type="password" class="form-control" name="j_password"
								placeholder="password">
						</div>
						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->
							<div class="col-sm-12 controls">
								<div class="col-md-6" style="padding-top:5px">
									<label> 
										<input id="login-remember" type="checkbox" name="_spring_security_remember_me" value="remember-me"> Remember me
									</label>
								</div>
								<input id="btn-login" type="submit" class="btn btn-success col-md-6" value="login">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 control">
								<div style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! 
									<a href="user/register"> <!-- onClick="$('#loginbox').hide(); $('#signupbox').show()" -->
										Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div> <!-- /container -->
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script>
	$(document).ready(function(){
		$("#loginform").validate({
			rules:{
				j_username:{
					required:true,
					email:true,
					maxlength:50
				},
				j_password:{
					minlength:3,
					maxlength:50
				}
			},
			errorPlacement:
				function(error, element){
					error.insertAfter(element.parent());
					error.css("color", "red");
			    }
		});
	});
	</script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
   <%--  <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> --%>
  </body>
</html>