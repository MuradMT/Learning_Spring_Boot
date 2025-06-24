package org.example.learning_spring_boot.student.controller;

import org.example.learning_spring_boot.common.dto.CommonDto;
import org.example.learning_spring_boot.student.dto.StudentMapper;
import org.example.learning_spring_boot.student.entity.Student;
import org.example.learning_spring_boot.student.entity.StudentProjection;
import org.example.learning_spring_boot.student.repo.IStudentRepo;
import org.example.learning_spring_boot.student.repo.StudentDataRepo;
import org.example.learning_spring_boot.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:63342")
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
    public List<Student> getStudents(@RequestParam(required = false,value = "page_number")Integer pageNumber,
                                     @RequestParam(required = false,value = "page_size")Integer PageSize){
        //return studentRepo.getStudents();
        if(pageNumber!=null){
            Pageable pageable = PageRequest.of(pageNumber, PageSize, Sort.by("id").descending());
            Page<Student> all = studentDataRepo.findAll(pageable);
            return all.getContent();
        }else{
            return studentDataRepo.findAll();
        }
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

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentDataRepo.save(student);
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable Integer id){
        Optional<Student> byId = studentDataRepo.findById(id);
        return byId.orElse(null);
    }

    @GetMapping(value="/allDtos")
    public CommonDto getAllStudents(@RequestParam(required = false, value = "name")String name, @RequestParam(required = false, value = "surname")String surname
                                        ){
        List<Student> all;
        if(name!=null && surname!=null){
            all = studentDataRepo.findAll(name,surname);
        }
         all=studentDataRepo.findAll();
//        return  all.stream().map(student ->toStudentDto(student))
//                .collect(Collectors.toList());
//        return new CommonDto()
//                .setObj( all.stream().map(StudentController::toStudentDto)
//                        .collect(Collectors.toList()))
//                .setMessage("All students found")
//                .setDateTime(LocalDateTime.now());
        return new CommonDto()
                .setObj( all.stream().map(StudentMapper.MAPPER::toDto)
                        .collect(Collectors.toList()))
                .setMessage("All students found")
                .setDateTime(LocalDateTime.now());
    }

//    private static StudentDTO toStudentDto(Student student){
//        return  new StudentDTO()
//                .setFirstName(student.getFirstName())
//                .setLastName(student.getLastName());
//    }

}
