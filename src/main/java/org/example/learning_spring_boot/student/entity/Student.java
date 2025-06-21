package org.example.learning_spring_boot.student.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Students")   // maps to your existing table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;            // lower-case

    @Column(name = "Name")
    private String firstName;      // lower-case camelCase

    @Column(name = "Surname")
    private String lastName;       // lower-case camelCase

    @Column(name = "Age")
    private Integer age; // lower-case

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private University university;

    public Student() { }

    public Student(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.age       = age;
    }

    // --- standard getters & setters ---

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='"  + lastName  + '\'' +
                ", age=" + age +
                '}';
    }
}