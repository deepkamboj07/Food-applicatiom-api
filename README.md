<h1>Food Application REST API with JWT Token Authentication and MySQL Database</h1>

This repository showcases an example of a secure RESTful API built with Spring Boot. The API utilizes JSON Web Tokens (JWT) for user authentication and authorization, while storing data in a MySQL database. Spring Security is employed to ensure the API's security.

<h2>Features</h2>
<ul>
  <li>User authentication and authorization using JWT tokens.</li>
  <li>MySQL database integration for storing and retrieving data.</</li>
  <li>Secure endpoints to protect sensitive resources.</li>
  <li>Configurable properties for easy customization</li>
</ul>


<h2>Technologies Used</h2>
<ul>
  <li>Spring Boot: A powerful framework for building Java applications.</li>
  <li>Spring Security: Provides authentication and authorization capabilities.</li>
  <li>JSON Web Tokens (JWT): A standard for securely transmitting information between parties.</li>
  <li>MySQL: A popular open-source relational database management system</li>
</ul>

<h2>Getting Started</h2>
To run the API locally, please follow these steps:
  
  <br/>
  <li>Clone the repository:</li><br/>

    $ git clone https://github.com/deepkamboj07/Food-applicatiom-api.git
  
  <br/><li>Configure the MySQL database connection in the application.properties file:</li><br/>
  
  ```
    spring.datasource.url=jdbc:mysql://localhost:3306/db_name<br/>
    spring.datasource.username=db_username<br/>
    spring.datasource.password=db_password
  ```
  
  <br/>
  <li>Build and run the application using Maven:</li><br/>

    $ cd your-repo
    $ mvn sping-boot:run
  
  <br/>
  <li>The API should now be running at http://localhost:3000</li>


  
<h2>Usage</h2>
The API provides the following endpoints:<br/>
<ul>
    <br/>
    <li>POST/api/auth/register - Register a new user.</li>
    <li>POST /api/auth/login - Authenticate and obtain a JWT token.</li>
    <li>GET /api/c/user/email?='your_email' - Get user information by there email (requires authentication JWT).</li>
    <li>GET /api/a/users - Get all users (requires authentication) but fetched only by Admin.</li>
    <li>POST /api/a/food-item Add food item only by admin role user</li>
    <li>GET /api/c/food-items - Get all food items in store</li>
    <li>DELETE /api/a/food-item/id?=id Delete food item only by admin role user</li>
    <li>POST /api/a/food-type Add food type only by admin role user</li>
    <li>GET /api/c/food-type - Get all food Types with their food item</li>
    <li>DELETE /api/a/food-type/id?=id Delete food Type only by admin role user</li>
    <li>GET /api/file/image/{name} -Get image resource</li>
    <li>POST /api/file/upload Upload image with authentication</li>
    
</ul>

<br/>
<h2>Contributions</h2>
Contributions to this project are welcome! If you have any suggestions or improvements, feel free to open an issue or submit a pull request.

<br/>
<h2>Contact</h2>
If you have any questions or need further assistance, please feel free to contact me at deepanshukamboj2023@gmail.com.

Enjoy building secure RESTful APIs with Spring Boot and JWT token authentication!




