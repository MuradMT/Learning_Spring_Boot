package org.example.learning_spring_boot;

import org.example.learning_spring_boot.student.entity.Student;
import org.example.learning_spring_boot.student.entity.StudentProjection;
import org.example.learning_spring_boot.student.repo.IStudentRepo;
import org.example.learning_spring_boot.student.repo.StudentDataRepo;
import org.example.learning_spring_boot.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final IStudentRepo studentRepo;
    //private final ApplicationContext applicationContext;
    private final StudentDataRepo studentDataRepo;

    public StudentController(StudentDataRepo studentDataRepo, @Qualifier("studentRepo") StudentRepo studentRepo, ApplicationContext applicationContext) {
        //this.studentRepo = studentRepo;
        //this.applicationContext = applicationContext;
        this.studentDataRepo = studentDataRepo;
        this.studentRepo = studentRepo;
    }
//    @GetMapping(value = "/allWithContext")
//    public List<Student> getStudentswithContext(){
//        StudentRepo studentRepo1 =(StudentRepo) applicationContext.getBean("studentRepo");
//        return studentRepo1.getStudents();
//    }
    @GetMapping(value = "/all")
    public List<Student> getStudents(){
        //return studentRepo.getStudents();
        return studentDataRepo.findAll();
    }
    @GetMapping(value="/allByName")
    public List<Student> getStudentsByName(){
        return  studentDataRepo.findByFirstName("Murad");
    }

    @GetMapping(value="/allByNameAndSurname")
    public List<Student> getStudentByNameAndSurname(){
        return studentDataRepo.findAll("Murad","Khan");
    }
    @GetMapping(value = "/allWithFullName")
    public List<StudentProjection> getStudentsWithFullName(){
        return studentDataRepo.fetchFullName();
    }

}
