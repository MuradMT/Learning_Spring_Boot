package org.example.learning_spring_boot.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity(name="University")
@Table(name = "Universities")
@DynamicUpdate
@NamedQuery(name = "findByName",query = "Select u from University u where u.name=:name")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "university", targetEntity = Student.class,cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnore
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
