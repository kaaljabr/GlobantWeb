<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Globant Web Login</title>
    <link href="<c:url value="/css/reset.css" />" rel="stylesheet">
    <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link href="<c:url value="/css/style.css" />" rel="stylesheet">
	
 </head>

  <body>


<div class="pen-title">
  <h1>Globant Web Login</h1><span>by <a href='https://www.linkedin.com/in/kareemjabr'>Kareem Jabr</a></span>
</div>
<!-- Form Module-->
<div class="module form-module">

  <div class="form">
    <h2>Login to your account</h2>
    <form id="newPersonForm">
      <input type="text" placeholder="Username" name="username" id="username"/>
      <input type="password" placeholder="Password" name="password" id="password"/>
     <input class="loginButton" type="submit" value="Login" />
	  
    </form>
  </div>
  <div class="cta">
	<div id="personFormResponse" class="green"> </div>
	<div class="error hide" id="usernameError">Username cannot be empty</div>
	<div class="error hide" id="passwordError">Password cannot be empty</div>
	</div>
 <!--  <div class="cta"><a href="http://andytran.me">Forgot your password?</a></div> -->
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	
	 <script type="text/javascript">
   
    $(document).ready(function() {
      // Save Person AJAX Form Submit
      $('#newPersonForm').submit(function(e) {
        // will pass the form data using the jQuery serialize function
        if(!validatePersonId()) 
          return false;
        $.post('${pageContext.request.contextPath}/submitLogin', $(this).serialize(), function(response) {
          $('#personFormResponse').text(response);
          $('#personFormResponse').show();
        });         
        e.preventDefault(); // prevent actual form submit and page reload
      });
       
    });
     
	function validatePersonId(personId) {
		var valid = true;
		$('#usernameError').hide();
	    $('#passwordError').hide();
		var username = $('#username').val();
		var password = $('#password').val();
	      if(username === undefined || username =="") {
	    	$('#personFormResponse').hide();
	        $('#usernameError').show();
	        valid = false;
	      }if(password === undefined || password==""){
	    	  $('#personFormResponse').hide();
	    	  $('#passwordError').show();
	    	  valid= false;
	      }
	     return valid;
	    }
     
   
  </script>
          
           
  </body>
</html>
