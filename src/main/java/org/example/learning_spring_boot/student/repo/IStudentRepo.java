package org.example.learning_spring_boot.student.repo;

import org.example.learning_spring_boot.student.entity.Student;

import java.util.List;

public interface IStudentRepo {
     List<Student> getStudents();
     void addStudent(Student student);
     void deleteStudent(int id);
     void updateStudent(Student student);
     Student getStudentById(int id);
}
