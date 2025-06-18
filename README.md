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

[12. Here](http://12.Here) in order to connect to database ,jpa has EntityManager.
**13**.We use 3 annotations for connect class to database table:

@Entity

@Id(mandatory when we create entity)

@Column(optional)

Tomcat automatically starts at background in spring boot.

Generally it sees our functions with annotations

as servlets in spring boot

EntityManager creates native query,so  we can use it in complex joins

We have also auto mapping with jpa

**14.**
`@RestController`**→tells browser it is controller**

15.
`@RequestMapping("/students")`→maps to the url,

we declare our controller url with this annotation

16.
`@GetMapping`→it tells us function  is http get request

alternative syntax
`@RequestMapping(method = RequestMethod.*GET* )`

17.
`@Component` helps us to create objects of all classes,so it is necessarry for dependency injection.

We generally use it for repositories.

**Reflection API** generally handles this process.The main reason of creation.

18.JPA Entity Manager uses proxy pattern background,we

do lazy-loading

19.Spring boot has automatic dependency injection and

inversion of control support,

DI means we can directly use repository object inside controller easily

Object instance created automatically, you do not manage it, then it  is **Inversion of control principle**

20.**Spring Container** - keeps all the object that need to create instance

for instance all repos services etc

21.**Spring Boot** is core ,uses spring framework tools and creates things automatically

such as built in DI, tomcat, Serialization etc, then we have 2 implementation on spring boot:

Restful for building apis backend with restController, simple controller to become mvc controller

22.**ApplicationContext→**it gives us just 1 specific component.Imagine inside controller we need to use 1 specific repo inside controller,so injecting it on top is [meangles.So](http://meangles.So) we can use in this case.But generally using application context is considered bad due to compilation errors and etc.

Example code snippet:

```java
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepo studentRepo;
    private final ApplicationContext applicationContext;
    
    public StudentController(StudentRepo studentRepo, ApplicationContext applicationContext) {
        this.studentRepo = studentRepo;
        this.applicationContext = applicationContext;
    }
    
    @GetMapping(value = "/allWithContext")
    public List<Student> getStudentswithContext(){
        StudentRepo studentRepo1 = (StudentRepo) applicationContext.getBean("studentRepo");
        return studentRepo1.getStudents();
    }
    
    @GetMapping(value = "/all") 
    public List<Student> getStudents(){
        return studentRepo.getStudents();
    }
}

```

In repository i just used :

`@Component(value = "studentRepo")`

23.`@Component(value = "studentRepo")` -we named component ,it helps to use inside app componenet.Component creates object ,but follows  **Singleton Pattern**. When we annotate class ,we create it is object just one time.

24.

`@Scope("singleton")`-Default IOC bean scope is singleton.

`@Scope("prototype")`-I tcreates a new instance everytime when we need it

25.Bean = an object managed by the Spring IoC container

We have base bean called Component,then other domain specific beans such as service,repository,controller etc.

| Concern | Annotation |
| --- | --- |
| Generic bean | `@Component` |
| Business/service layer | `@Service` |
| Persistence/DAO layer | **`@Repository`** |
| Web/controller layer | `@Controller` |

26.Spring boot has **Embedded Tomcat**

27.

In application properties file we can modify server port,main context path.

`server.port=9090`

`server.servlet.context-path=/api`

28.We have Enterprise Java Beans before, that used tomee.But it is old,not used and has lot of manual setup.

1. In java we also follow **loose coupling principle,**

so we create interfaces for repo then inject interface in controller.We also followed SOLID **dependency inversion. Tightly coupling is bad practise,prevent it.**

30.

At the heart of the Spring Framework lies the **Core Container**, often just called **Spring Core**. It provides the fundamental building blocks you use in every Spring app:

---

## Inversion of Control (IoC) & Dependency Injection (DI)

So Spring core used in console apps etc,carries all base features.But not popular.Spring boot has all Spring core feature.

Has 2 types:XML and normal Java classes **XML has hard syntax,no compilication error etc.**

31.

| Annotation | Intent |
| --- | --- |
| `@Component` | General-purpose stereotype. Marks a class as a Spring-managed bean. |
| `@Configuration` | Indicates a **configuration class** whose methods (`@Bean`) produce other beans. |
- **`@Component`** is the most generic: any class annotated with it will be detected and instantiated as a bean.
- **`@Configuration`** is itself meta-annotated with `@Component`, but signals to Spring “this class contains `@Bean`methods to define other beans.”

Syntax:

```java
@Configuration
public class MyBeanConfig {
    @Bean
    public String myBean() {
        return "MyBean";
        //generally here we create object
        //we do configurations that we can not add any annotations to class
        //for instance we have some old built in libraries we want to inject them
        //But we can not,so we use conf+ bean combo 
    }
}
```

So when we use componenet and similar ones they create beans directly for class,but configuration is used to initiall load classs itself and then add bean methods to IOC
