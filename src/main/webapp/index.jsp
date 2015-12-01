<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE HTML>
<html>
  <head>
    <title>Spring MVC - Ajax</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
      body { background-color: #eee; font: helvetica; }
      #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
      .green { font-weight: bold; color: green; }
      .message { margin-bottom: 10px; }
      label { width:70px; display:inline-block;}
      .hide { display: none; }
      .error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>
   
  <div id="container">
   
   <h2>Submit new Person</h2>
    <form id="newPersonForm">
      <label for="nameInput">Username: </label>
      <input type="text" name="username" id="usernameInput" />
      <br/>
       
      <label for="passwordInput">Password: </label>
      <input type="password" name="password" id="passwordInput" />
      <br/>
      <input type="submit" value="Login" /><br/><br/>
      <div id="personFormResponse" class="green"> </div>
    </form>
  </div>
   
   
  <script type="text/javascript">
   
    $(document).ready(function() {
      // Save Person AJAX Form Submit
      $('#newPersonForm').submit(function(e) {
        // will pass the form data using the jQuery serialize function
        $.post('${pageContext.request.contextPath}/login', $(this).serialize(), function(response) {
          $('#personFormResponse').text(response);
        });
         
        e.preventDefault(); // prevent actual form submit and page reload
      });
       
    });
     
    function validatePersonId(personId) {
      console.log(personId);
      if(personId === undefined || personId < 0 || personId > 3) {
        $('#idError').show();
        return false;
      }
      else {
        $('#idError').hide();
        return true;
      }
    }
     
   
  </script>
   
  </body>
</html>