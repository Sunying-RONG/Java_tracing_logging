# TPtrace
It's a simple application using Spring boot, Mariadb, Thymeleaf to practice tracing and logging.

With this web application, we can 
- create a new user, 
- choose a user to login, 
- login user create a new product,
- display list of products
- login user check (read) a product,
- login user update/delete (write) a product
  
Use Log4j2 to print logs, information including operation type, user information, time, etc.

Save logs to application.log in JSON format which is at root of project 

applicationExample.log is an example of generated logs for different users with different operations which is used to create profiles.

Create 3 profiles "Read / Write / Read most expensive object", save generated profiles in JSON format in applicationProfil.json


- Git clone project
- Import project "TPtrace" in Eclipse.
- Run "TPtrace -> src/main/java -> com.example.TPtrace -> TPtraceApplication.java" as Java Application.
- In browser, navigate to http://localhost:8080/index
- However database is on my local machine
