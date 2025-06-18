<h1>All notes I took during learning spring boot:</h1>
# **Spring notes**

1.**Spring** is a **powerful framework** for building **Java applications**, especially web apps and backend services.

2.Spring initializer is best place to start spring app without depending on ides

3.Gradle is modern bult/dependency tool that uses grrovy tool

4.Jvm languages is java kotlin groovy scala 

5.

**Spring Boot**

= Spring Framework + Auto Configuration + Embedded Server + Fast Setup

**Spring Framework (Core Spring)**

= Powerful but requires manual setup

6.

**JPA** stands for **Java Persistence API**.

It’s a **standard specification** (not a library) in Java for **working with relational databases** using Java objects.

> JPA lets you map Java classes to database tables and work with data using objects — instead of writing raw SQL.
> 

7.Hibernate is ORM, implementation of JPA.

8.When we create project the most essential dependencies is mysql driver,spring data jpa,spring web.

9.SDKMAN Manages **Java SDKs** (and other JVM tools like Gradle, Kotlin, etc.)

10.Groovy USES FUNCTIONS AS NAME {},OR VARIABLES JUST SIMPLY DECLARING IT

for instance we do project.delete etc

Gradle helps us to handle dependencies and lifecycle things,

1. [In](http://11.In) application properties we set our connection string to connect database:

`spring.datasource.url=jdbc:mysql://localhost:3306`

`spring.datasource.username=root`

`spring.datasource.password=`

`spring.datasource.driver-class-name=com.mysql.cj.jdbc`

it is appsetting.json file c# alternative

[12.Here](http://12.Here) in order to connect to database ,jpa has EntityManager.
