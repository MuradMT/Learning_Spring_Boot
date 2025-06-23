package org.example.learning_spring_boot.student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StudentDTO {

    private String firstName;

    @JsonProperty("surname")
    private String surname;


}
