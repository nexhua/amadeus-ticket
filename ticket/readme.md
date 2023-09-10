Case Solution for Amadeus


I have provided a application.properties in the filepath 

[Application Properties Template](https://github.com/nexhua/amadeus-ticket/blob/master/ticket/src/main/resources/templates/application.properties.template)

You only need to change the <Your-Password> section so Spring Data can use that configuration to connect to a local PostgreSQL instance.

To access any endpoint you need to be authenticated. There is a only one user which has the following properties;

username: amadeus
password: password123

You can access the Swagger UI via goint to /swagger-ui/index.html

[Swagger UI](http://localhost:8080/swagger-ui/index.html)
