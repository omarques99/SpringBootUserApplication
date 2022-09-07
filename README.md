# SpringBootUserApplication

In Simple SpringBoot Application we are able to register new users to our application and store their information in a Local Database.
Once the user is created, they can login using their credentials (email and password) to have access the list of users in the database, and can then use operations like 
delete a user, or search a user by ID. 

This project uses programming languages like JavaScript and HTML for the front end, and Java and SQL to accomplish the 
back-end idea, and manipulation of data.

This is just a simple starting application using concepts like springboot, hibernate, JWT, API Rest, and database.

Files implemented include:
 FRONT-END
- users.html
- login.html
- register.html
- login.js
- register.js
- Users.js

BACK-END

  "Controllers"
- UserController.java
- AuthController.java

  "Data Access Object"
- UserDao.java
- UserDaoImplementation.java

  "Model"
- User.java

  "Utils"
- JWTUtil.java
