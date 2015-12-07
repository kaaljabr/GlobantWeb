<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Globant Web Login</title>
<link href="<c:url value="/css/reset.css" />" rel="stylesheet">
<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

</head>

<body>


	<div class="pen-title">
		<h1>Globant Web Login</h1>
		<span>by <a href='https://www.linkedin.com/in/kareemjabr'>Kareem Jabr</a></span>
	</div>
	<!-- Form Module-->
	<div class="module form-module">
		<div class="toggle">
			<i class="fa fa-times fa-pencil"></i>
			<div class="tooltip">Click Me</div>
		</div>
		<div class="form">
			<h2>Login to your account</h2>
			<%-- <form id="loginForm"> --%>
			<form:form id="loginForm" modelAttribute="user" action="login">
				<form:errors path="username" cssClass="error"/>
				<input type="text" placeholder="Username" name="username" id="username" />				
				<form:errors path="password" cssClass="error"/> 
				<input type="password" placeholder="Password" name="password" id="password" />				
				<input class="loginButton" type="submit" value="Login" />
			</form:form>				
			<%-- </form> --%>
		</div>
		 <div class="form">
		    <h2>Create an account</h2>
		    <form id="registerForm">
		      <input type="text" placeholder="Username" name="username" id="username"/>
		      <input type="password" placeholder="Password" name="password" id="password"/>
		      <input type="text" placeholder="City" name="city" id="city"/>
		      <input type="text" placeholder="Profession" name="profession" id="profession"/>
		      <input class="loginButton" type="submit" value="Regsiter" />
		    </form>
		  </div>
		<div class="cta">
			<div id="responseMsg" class="error"></div>
			<!-- <div class="error hide" id="usernameError">Username cannot be empty</div>
			<div class="error hide" id="passwordError">Password cannot be empty</div> -->
	
		</div>		
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="<c:url value="/js/index.js" />"></script>

	<script type="text/javascript">
		 $(document).ready(function() {
			var registerStatus = getURLParameter('register');
			console.debug('debug me pleas '+registerStatus);
			if(registerStatus == "success"){
				$('#responseMsg').text("User successfully created!!");
				$('#responseMsg').show();	
			}
			// handle registration as JSON and post to the controller
			$('#registerForm').submit(function(e) {
				//validate for required fields
				/* if (!validateLoginFrom())
				return false; */
				// if validation passed, will pass the form data using the jQuery serialize function
				$.post('${pageContext.request.contextPath}/createUser',$(this).serialize(),function(response) {
					if(response != "success"){
						$('#responseMsg').append(response);
						$('#responseMsg').show();	
					}else{						
						window.location.replace("${pageContext.request.contextPath}/login?register=success");
					}					
				});
			e.preventDefault(); // prevent actual form submit and page reload
			});			
		});

		function validateLoginFrom() {
			var valid = true;
			$('#usernameError').hide();
			$('#passwordError').hide();
			var username = $('#username').val();
			var password = $('#password').val();
			if (username === undefined || username == "") {
				$('#personFormResponse').hide();
				$('#usernameError').show();
				valid = false;
			}
			if (password === undefined || password == "") {
				$('#personFormResponse').hide();
				$('#passwordError').show();
				valid = false;
			}
			return valid;
		}
		
		function getURLParameter(sParam){
		   var sPageURL = window.location.search.substring(1);
		    var sURLVariables = sPageURL.split('&');
		    for (var i = 0; i < sURLVariables.length; i++){
		        var sParameterName = sURLVariables[i].split('=');
		        if (sParameterName[0] == sParam){
		            return sParameterName[1];
		        }
		    }
	    }
	</script>


</body>
</html>
