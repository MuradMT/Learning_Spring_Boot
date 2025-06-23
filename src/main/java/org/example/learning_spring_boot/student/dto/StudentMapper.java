package org.example.learning_spring_boot.student.dto;

import org.example.learning_spring_boot.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "surname",source = "lastname")
     StudentDTO toDto(Student student);
    @Mapping(target = "lastname",source = "surname")
    Student toEntity(StudentDTO studentDTO);
//    default StudentDTO toStudentDto(Student student){
//        return  new StudentDTO()
//                .setFirstName(student.getFirstName())
//                .setLastName(student.getLastName());
//    }
}
