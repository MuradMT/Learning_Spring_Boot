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

32.We have persistence.xml in old projects,acts as application properties

33.OOP ORM specification,JPA is java version of orm specification

34.JPA implementations are Hibernate,EclipseLink

**Default ORM** is Hibernate

35.**Spring Data** exists to **dramatically simplify** and **standardize** the way you interact with databases (both SQL and NoSQL) in Spring applications. Instead of writing tons of DAO boilerplate, you get:

---

### 1. Repository Abstraction & CRUD Methods for Free

By extending one of Spring Data’s repository interfaces, you immediately inherit methods like `save()`, `findById()`, `findAll()`, `delete()`, and more:

```java

public interface UserRepository extends JpaRepository<User, Long> { }
```

You don’t have to write implementations—Spring Data auto-generates them at runtime.

There is a proxy pattern in background again.We have done this one in C# manually(fucking annoying).

### 2. Query Derivation from Method Names

Need to look up users by email or status? Just add methods to your interface:

```java
java
List<User> findByStatus(Status status);
Optional<User> findByEmail(String email);//can return list or not
//Optional does not force you to return exact same thing

```

Spring Data parses those method names and builds the corresponding JPQL/SQL (or MongoDB, Cassandra CQL, etc.) queries automatically.

### 3. Native Sql Query support

```java
@Query(nativeQuery = true, 
value = "select * from Students where Name=:name and Surname=:surname")
List<Student> findAll(String name, String surname);
```

### 4. Projection support in order to get values from joins,concats etc

```java
public interface StudentProjection 
      {    
      String getFirstName();    
      String getLastName();    
      String getFullName();
      }
      
@Query(
            nativeQuery = true,
            value = """
Select Name as firstname, Surname as lastname, 
CONCAT(Name ,' ' ,Surname) as fullname from Students
            """
    )
    List<StudentProjection> fetchFullName();
```

Here we should match sql values to our projection class getters, we use as here.

36.JPQL-is java persistence query language, alternative to sql.When we use entity Manager we can do **createQuery()** not create native query.Syntax will be select s from Students s.But we do not prefer this one, because the performance is bad. Her[e](http://bad.Here) functions represent sql procedures.

37.Spring’s IoC container supports three primary styles of injecting dependencies into your beans:

---

## 1. Constructor Injection

Spring calls your bean’s constructor and passes in all required collaborators.

```java
java
@Component
public class OrderService {
  private final PaymentGateway gateway;

  // Spring will autowire this constructor
  @Autowired
  public OrderService(PaymentGateway gateway) {
    this.gateway = gateway;
  }
}

```

- **Pros:**
    - Ensures required dependencies are never `null`
    - Promotes immutability (mark your fields `final`)
    - Easier to write unit tests (no container needed)
- **When to use:**
    - For mandatory dependencies
    - Recommended in modern Spring (and required under Spring Boot if a bean has exactly one constructor—the `@Autowired` can even be omitted).

---

## 2. Setter Injection

Spring calls a no-arg constructor, then invokes setter methods to supply dependencies.

```java
java
@Component
public class ReportGenerator {
  private DataSource ds;

  @Autowired
  public void setDataSource(DataSource ds) {
    this.ds = ds;
  }
}

```

- **Pros:**
    - Allows for optional dependencies (you can omit calling the setter)
    - Good when you may need to reconfigure a dependency at runtime
- **Cons:**
    - Bean can be in an “incomplete” state between instantiation and setter call
    - Harder to enforce required dependencies

---

## 3. Field Injection

Spring injects directly into your private fields via reflection:

```java
java
@Component
public class NotificationService {
  @Autowired
  private EmailClient emailClient;
}

```

- **Pros:**
    - Very concise—no boilerplate constructor or setter
- **Cons:**
    - Harder to test (you need reflection or a Spring context)
    - Encourages mutable state
    - Considered an anti‐pattern by many (less explicit)

  38.When Spring finds multiple candidate beans for a single injection point, it needs a way to decide which one you actually want. That’s where **`@Primary`** and **`@Qualifier`** come in.
    
  ---

  ## `@Primary` — the default choice

    - **What it does:** Marks one bean as the *default* when more than one matches by type.
    - **Where to put it:** On the bean definition (class or `@Bean` method).
    - **How it works:** If you `@Autowired MyService svc;` and there are three `MyService` beans in the context but one is annotated `@Primary`, Spring injects that one.

    ```java
    java
    CopyEdit
    @Service
    @Primary
    public class PaypalPaymentService implements PaymentService {
       // …
    }
    
    @Service
    public class StripePaymentService implements PaymentService {
       // …
    }
    
    // Somewhere else
    @Component
    public class OrderProcessor {
      private final PaymentService payment;
    
      // No need for @Qualifier: PaypalPaymentService is @Primary
      public OrderProcessor(PaymentService payment) {
        this.payment = payment;
      }
    }
    
    ```
    
  ---

  ## `@Qualifier` — pick by name (or custom qualifier)

    - **What it does:** Lets you disambiguate *exactly* which bean you want, by name or by a custom qualifier annotation.
    - **Where to put it:**
        - On the bean: `@Service("stripe")` or `@Qualifier("stripe")`
        - On the injection point: `@Autowired @Qualifier("stripe") PaymentService payment;`
    - **How it works:** Spring matches the qualifier value against the bean’s name or its `@Qualifier` metadata.

    ```java
    java
    CopyEdit
    @Service("paypal")
    public class PaypalPaymentService implements PaymentService { … }
    
    @Service("stripe")
    public class StripePaymentService implements PaymentService { … }
    
    @Component
    public class OrderProcessor {
    
      private final PaymentService payment;
    
      public OrderProcessor(
          @Qualifier("stripe") PaymentService payment  // explicitly choose stripe
      ) {
        this.payment = payment;
      }
    }
    
    ```

  You can also define **custom qualifier annotations**:

    ```java
    java
    CopyEdit
    @Target({ FIELD, PARAMETER, TYPE })
    @Retention(RUNTIME)
    @Qualifier
    public @interface FastPayment { }
    
    @FastPayment
    @Service
    public class StripePaymentService implements PaymentService { … }
    
    @Component
    public class OrderProcessor {
      public OrderProcessor(@FastPayment PaymentService payment) {
        this.payment = payment;  // injects StripePaymentService
      }
    }
    
    ```
    
  ---

  ## Combining `@Primary` and `@Qualifier`

    - You can put `@Primary` on your “usual” bean so most injections pick it up.
    - Use `@Qualifier` only where you need one of the other beans.

    ```java
    java
    CopyEdit
    @Service
    @Primary
    public class PaypalPaymentService implements PaymentService { … }
    
    @Service
    public class StripePaymentService implements PaymentService { … }
    
    @Component
    public class DefaultProcessor {
      public DefaultProcessor(PaymentService payment) { /* gets Paypal */ }
    }
    
    @Component
    public class StripeOnlyProcessor {
      public StripeOnlyProcessor(
          @Qualifier("stripePaymentService") PaymentService payment
      ) { /* gets Stripe */ }
    }
    
    ```
    
  ---

  ### When to use which

    - **`@Primary`** for the bean you want used 99% of the time.
    - **`@Qualifier`** for the rare cases where you need a different implementation.

  Together they give you precise control over which implementation Spring injects whenever more than one matches by type.


  39.We change object in 2 ways:

  **1.Proxy Pattern**

  **2.Reflection API**