package org.example.learning_spring_boot.student.repo;

import org.example.learning_spring_boot.student.entity.Student;
import org.example.learning_spring_boot.student.entity.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDataRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFirstName(String name);

    @Query(nativeQuery = true,value = "select * from Students where Name=:name and Surname=:surname")
    List<Student> findAll(String name,String surname);

    @Query(
            nativeQuery = true,
            value = """
Select Name as firstname, Surname as lastname, CONCAT(Name ,' ' ,Surname) as fullname from Students
            """
    )
    List<StudentProjection> fetchFullName();

}
