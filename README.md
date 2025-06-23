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

40.Transaction,commit,rollback

Example code snippet:

```java
@Override
public void deleteStudent(int id) 
{       
final EntityTransaction transaction = entityManager.getTransaction();  
     try{           
     transaction.begin();           
     final Student student = entityManager.find(Student.class, id); 
               entityManager.remove(student);           
               transaction.commit();       
               }catch(Exception e){           
               transaction.rollback();       
               }}
```

41.
Enabling printing SQL:
`logging.level.org.hibernate.sql=DEBUG`

42.**Hard  vs Soft Delete**

43.**Attached**-everything happens inside transaction

**Detached**-something happens outside transaction

For isntance we find element outside transaction,but delete inisde transaction

so we create a detached object here.

44.The **`CriteriaBuilder`** is the entry point to JPA’s *Criteria API*, a type-safe, programmatic way to build queries instead of writing JPQL strings. It lives in the `jakarta.persistence.criteria` package (or `javax.persistence.criteria` if you’re on older versions).

---

## Why use the Criteria API?

- **Type safety**: your IDE/compiler will catch typos in field names.
- **Dynamic queries**: you can conditionally add `where` clauses, `order by`, joins, etc., at runtime.
- **Refactoring-friendly**: renaming your entity’s fields automatically propagates to your criteria code.

---

## The typical workflow

1. **Obtain a `CriteriaBuilder`** from your `EntityManager`:

    ```java
    java
    CopyEdit
    CriteriaBuilder cb = em.getCriteriaBuilder();
    
    ```

2. **Create a `CriteriaQuery<T>`** for the result type `T`:

    ```java
    java
    CopyEdit
    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
    
    ```

3. **Define a “root”** (the `FROM` clause) and any joins:

    ```java
    java
    CopyEdit
    Root<Student> root = cq.from(Student.class);
    
    ```

4. **Build predicates** (the `WHERE` clause) with helper methods on `CriteriaBuilder`:

    ```java
    java
    CopyEdit
    Predicate ageGt18   = cb.greaterThan(root.get("age"), 18);
    Predicate lastNameA = cb.like(root.get("lastName"), "A%");
    
    ```

5. **Compose your query** by chaining methods on `CriteriaQuery`:

    ```java
    java
    CopyEdit
    cq.select(root)
      .where(cb.and(ageGt18, lastNameA))
      .orderBy(cb.asc(root.get("firstName")));
    
    ```
45.
`@ComponentScan(basePackages = "org.example.test")`

Component scan allows us to use any components in different package.

46.@RequestParam annotation helps us to get query parameters.We can do this one with servlet also.We can make it optional with required.

```java

@RequestParam(required = false,value = "page_number")
```

The `value` parameter **specifies the name of the query parameter** in the request URL that you want to bind to a method argument.

---

```java
java
CopyEdit
@GetMapping("/greet")
public String greet(@RequestParam(value = "name") String userName) {
    return "Hello, " + userName;
}

```

If a client sends a request like:

```
pgsql
CopyEdit
GET /greet?name=Murad

```

Then `userName` will be `"Murad"`.

47.We have JPARepository and CRUDRepository.Crud one only covers the basic functionalities,but JPA covers everything that crud one covers.The advantages of using JPA over Crud one is:

```java
**//1.Sorting Functionality:**
return studentDataRepo.findAll(Sort.*by*("id").descending());
```

```java
//2.Pagination Functionality(Sorting also covered inside pagination)
@GetMapping(value = "/all")
public List<Student> getStudents(
@RequestParam(required = false,value = "page_number")Integer pageNumber,
@RequestParam(required = false,value = "page_size")Integer PageSize)
{  
             if(pageNumber==null){        
             Pageable pageable = PageRequest.*of*(pageNumber, PageSize, 
             Sort.*by*("name").descending());        
             Page<Student> all = studentDataRepo.findAll(pageable);        
             return all.getContent();    
             }else{        
             return studentDataRepo.findAll();    
             }}
```

48.Here’s a breakdown of **all essential JPA relationship annotations** used in entity modeling. These annotations define how objects relate to each other and how these relationships are mapped to the database.

---

### 🔹 1. `@ManyToOne`

**Many (A) → One (B)**

Used when many entities relate to a single entity.

```java
java
CopyEdit
@ManyToOne
@JoinColumn(name = "university_id")
private University university;

```

- This is the **owning side**.
- Foreign key goes in the `many` side table (`Student`).
- Default fetch type: `EAGER`.

---

### 🔹 2. `@OneToMany`

**One (B) → Many (A)**

Used on the inverse side of a `@ManyToOne` relationship.

```java
java
CopyEdit
@OneToMany(mappedBy = "university")
private List<Student> students;

```

- This is the **non-owning side**.
- `mappedBy` points to the field in the owning entity.

---

### 🔹 3. `@OneToOne`

**One (A) ↔ One (B)**

Used when one entity has a unique relationship with another.

```java
java
CopyEdit
@OneToOne
@JoinColumn(name = "passport_id")
private Passport passport;

```

- Owning side uses `@JoinColumn`.
- Default fetch type: `EAGER`.

**Bidirectional Example:**

```java
java
CopyEdit
@OneToOne(mappedBy = "passport")
private Student student;

```

---

### 🔹 4. `@ManyToMany`

**Many (A) ↔ Many (B)**

Used when multiple entities relate to multiple others.

```java
java
CopyEdit
@ManyToMany
@JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private List<Course> courses;

```

- Creates a **join table** to represent the relationship.
- `@JoinTable` is optional but gives control over table/column names.
- Default fetch type: `LAZY`.

### 🔹 Optional: Other Supporting Annotations

- `@JoinColumn`: Customizes the foreign key column.
- `@JoinTable`: Defines join table for many-to-many.
- `mappedBy`: Indicates inverse side (non-owning).
- `fetch = FetchType.LAZY` or `EAGER`: Controls loading strategy.We have optional [parameter.It](http://parameter.It) is interview question.
- `@OnDelete(action = OnDeleteAction.*NO_ACTION*)`
- We  have cascade type selection also

```java
@JsonIgnore helps us to prevent recursion in related tables,
like imagine we have one to many relationship,then we will use 
this one or vice versa.
```

49.

🔹 Default Jackson Behavior

By default, **Jackson will fail** if it encounters a "bean" with no serializable properties.

You can tell Jackson **not to fail** when it encounters such cases: Global config in Spring Boot (`application.properties`)

```
properties
CopyEdit
spring.jackson.serialization.fail-on-empty-beans=false

```

This happens when we try to do lazy [loading.In](http://loading.In) lazy loading we do not load things fully.And when we see something lazy it throws [exception.So](http://exception.So) this command prevents this behaviour.

50.Automatically creating tables in java to sql

```
properties
CopyEdit
spring.jpa.hibernate.ddl-auto=update

```

- Checks existing tables.
- Adds missing tables/columns if needed.
- **Does not drop or remove columns or data.**
- Safe-ish for local dev, but not recommended for production.

---

### Production Recommendation

- Use **Flyway** or **Liquibase** for production schema versioning.
- Set:

```
properties
CopyEdit
spring.jpa.hibernate.ddl-auto=validate

```

to ensure the schema matches without altering it automatically.

51.@DynamicUpdate-Specifies that SQL update statements for the annotated entity are generated dynamically, and only include columns which are actually being updated.

52.@NamedQuery-helps us to write query in annotation and give it name,then use it with entity manager.

```java

@NamedQuery(name = "findByName",query = "Select u from University u where u.name=:name")
```

```java
entityManager.createNamedQuery("findByName", University.class)        .getResultList();
```

1. `@GeneratedValue(strategy = GenerationType.*IDENTITY*)` -here we have different strategies.we can choose identity,sequence &table ,uuid etc. auto  selects default type sequence.
2. In order to prevent circular dependency in spring,we generally use `@Lazy` annotation.
3. `@RequestBody Student student` allows us to get data from request body.
4. Looked at how to do post request in postman,json format.
5. @Transactional is used when we create custom jpa repo,not in spring data.

```java
@Transactional 
public void addStudent(Student student) {
       entityManager.persist(student);
 }
 
```

Transactional belongs only to this function,if we use other functions inside it then ,then we should annotate them also.INTERVIEW QUESTION.***readonly=true*** allows us only to do read operations,blocks write operations.

58.jpa,boot,data difference,CAP theorem,ACID.

59.`@Transactional(rollbackFor = {NullPointerException.class, SQLException.class})` we can do rollback for all exception types or specific exception type such as SQLException.And we have additionally norollbackfor.We have ***timeout***,finishes transaction after if we do not find anything.

60.`@Transactional(propagation = Propagation.*REQUIRED*)` In Spring, **`propagation`** refers to how **transaction boundaries** behave when a method that runs in a transaction is called from another method that may or may not already be within a transaction.

| Propagation Type | Behavior |
| --- | --- |
| `REQUIRED`*(default)* | Joins the existing transaction if one exists; otherwise, creates a new one. |
| `REQUIRES_NEW` | Suspends the existing transaction (if any) and **always starts a new one**. |
| `NESTED` | Executes within a **nested** transaction if a parent exists; otherwise behaves like `REQUIRED`. Requires a savepoint-capable datasource (e.g., JDBC). |
| `SUPPORTS` | Executes within a transaction if one exists; otherwise runs **non-transactionally**. |
| `NOT_SUPPORTED` | Always runs **non-transactionally**, suspending any existing transaction. |
| `NEVER` | Runs only when there’s **no transaction**. Throws exception if a transaction exists. |
| `MANDATORY` | Requires an existing transaction. Throws exception if none exists. |

61.In Spring, **`@Transactional(isolation = Isolation.X)`** controls the ***transaction isolation level***, which defines how concurrent transactions interact with each other — especially regarding **data visibility** and **modification**.

---

### Why Isolation Matters

Isolation levels help prevent concurrency issues such as:

| Issue | Description |
| --- | --- |
| **Dirty Read** | Reading uncommitted data from another transaction. |
| **Non-repeatable Read** | A row value changes between two reads in the same transaction. |
| **Phantom Read** | A new row appears when the same query is run again in the same transaction. |

---

### Common Isolation Levels in Spring (`org.springframework.transaction.annotation.Isolation`)

| Isolation Level | Prevents | Description |
| --- | --- | --- |
| `DEFAULT` | – | Uses the default isolation level of the underlying DB (typically `READ_COMMITTED`). |
| `READ_UNCOMMITTED` | – | Allows dirty reads; lowest isolation level. |
| `READ_COMMITTED` *(default in many DBs)* | Dirty reads | Only committed data is visible. |
| `REPEATABLE_READ` | Dirty & non-repeatable reads | Ensures values remain the same when read multiple times. |
| `SERIALIZABLE` | Dirty, non-repeatable, phantom reads | Highest isolation; transactions run sequentially. Most restrictive and slowest. |