package org.example.learning_spring_boot.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Students")
public class Student {
    @Id
    private int Id;
    @Column(name = "Name")
    private String Firstname;
    @Column(name = "Surname")
    private String Lastname;

    public Student() {

    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    private int Age;

    public Student(String name, String surname,int age) {
        Firstname = name;
        Lastname = surname;
        Age=age;
    }

    public String getName() {
        return Firstname;
    }

    public void setName(String name) {
        Firstname = name;
    }

    public String getSurname() {
        return Lastname;
    }

    public void setSurname(String surname) {
        Lastname = surname;
    }

    @Override
    public String toString() {
        return "Name:"+this.getName()+" Surname:"+this.getSurname()+"Age:"+this.getAge();
    }
}
