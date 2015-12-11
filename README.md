# GlobantWeb
### Globant Code Challenge

#### JAVA/J2EE Technologies used in this project:
* Java JDK 1.8
* Tomcat Server (version 7.0)
* Maven as a build automation tool
* Spring Web MVC (version 4.2.3)
* JSP for views
* Hibernate JPA for persisting layer (version 5.0.4)
* Junit for testing (version 4.12)
* Jackson for JSON mapping (version 1.9.12)
* Dozer for mapping different bean versions (version 5.5.1)

#### Database used:
* MySQL DB (version 5.6), install and create a schema called "globant" (IMPORTANT)

Check out the wiki page for more details on building and running this project [Wiki](https://github.com/kaaljabr/GlobantWeb/wiki)

#### Here are wiki broken down into different section
[Import in Eclipse](https://github.com/kaaljabr/GlobantWeb/wiki/Import-the-project-into-Eclipse)  

[Build in Maven](https://github.com/kaaljabr/GlobantWeb/wiki/Build-the-project-in-Maven)  

[Run on Server](https://github.com/kaaljabr/GlobantWeb/wiki/Run-on-Tomcat-Server)  

[Web Application View](https://github.com/kaaljabr/GlobantWeb/wiki/Check-the-web-application-in-browser)  

[Rest API](https://github.com/kaaljabr/GlobantWeb/wiki/Check-the-REST-API)  


### WHy Spring?  

* Spring is a lightweight J2EE framework, Easy to develop and configure, powerful, straight forward. 
* Clear separation of roles like controllers, validators, model object, view resolvers and others.
* Easy to autowire components and give dependency to clients.
* Great integration with Hibernate JPA for accessing the persistent layer.
* JSP and tag libraries where it is easy to create html dynamically and bind form data.
* View resolvers and flexibility to return response and render views.
* Beans life cycle is scoped in a HTTP request or session. 

### Why hibernate/JPA?

* Since it is Object/Relational Mapping (ORM) framework, avoids low level JDBC and SQL code. 
* Easy to use and do both basic and complex queries.
* Hibernate supports lazy initialization, numerous fetching strategies and optimistic locking with automatic versioning and time stamping.
* Hibernate is reliable and stable.
* Easy to change your database, by changinf dialect.


### JUnit

* I used JUnit to test my service layer methods. And make sure they return the expected result or throw the expected exception. I used autowiring to inject the services I would like to test. Service layer calls DAO layer to do the persistent work.
* Test class is TestService.java, you can test by running JUnit test in eclipse. Or by running the maven command "test". Another third way is when executing a build by Maven don't skip the tests. Maven build will be executed to along with the tests.
* make sure to edit the directory path and files in resources>config.properties  
```
test.correct.dir.path=c:\\test_dir
test.filename=test.txt
```
Make sure to create the respective dir and file

### JSP, JQuery and CSS

For the front end part JSP views are used to display content. Javascript is used as well with JQuery library to handle some front end interaction and rendering.  
Like when user wants to register a user you click on a tooltip called register which in turn calls toggle function to display the user registration form. If you click it again it toggles back to the login form.


### Posting JSON payload
* When user logs in to the system the data is sent to the controller by posting the form data as in action post with username and password populated in User bean. (Refreshes the page in case of errors)
* But in case of user registration JQuery ajax post is used to submit the user registration form data as JSON payload to the controller endpoint register method.  (Does not refresh the page in case of errors)
####I used two ways to show the difference between the two different scenarios 


### Validation 
#### Two validation ways used in mainController
* First one is using the cutsom validator when the user logs in, using LoginValidator and validating logic defined in validate() mthod 
* Second way is the auto validate option by using the @Valid on the user parameter. In this case User bean has to define the validation annotaion like @Size(min = 4, max = 12, message = "username.min.max") 

  
### Rest API versioning
* I used URL version here like rest/v1/users & rest/v2/users. This way is used when there is a requirement to change the current behavior or the type of data returned. But some legacy system is still working on the older version of the data type, and there is no need to change the code on the legacy system side. So we have to provide a new version URL path to reflect the new updates for the new clients. 
* There is another way to provide the version in the header of the request. I can integrate this kind of versioning but I don't recommend if the rest API is going to be used by public people since they are not aware on how to provide a new attribute in the header of their reuqest.
* Another header way of doing it is the accept attribute.  





