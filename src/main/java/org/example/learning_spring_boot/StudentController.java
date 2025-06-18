package org.example.learning_spring_boot;

import org.example.learning_spring_boot.student.entity.Student;
import org.example.learning_spring_boot.student.repo.IStudentRepo;
import org.example.learning_spring_boot.student.repo.StudentRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final IStudentRepo studentRepo;
    //private final ApplicationContext applicationContext;

    public StudentController(StudentRepo studentRepo /*ApplicationContext applicationContext*/) {
        this.studentRepo = studentRepo;
        //this.applicationContext = applicationContext;
    }
//    @GetMapping(value = "/allWithContext")
//    public List<Student> getStudentswithContext(){
//        StudentRepo studentRepo1 =(StudentRepo) applicationContext.getBean("studentRepo");
//        return studentRepo1.getStudents();
//    }
    @GetMapping(value = "/all")
    public List<Student> getStudents(){
        return studentRepo.getStudents();
    }
}
