package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentId implements Serializable {
    private Long studentId;
    private Integer studentClass;
    private Integer studentRollNumber;
}
