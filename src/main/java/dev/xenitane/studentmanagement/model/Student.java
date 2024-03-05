package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Student implements Serializable {
    private Long student_id;
    private String student_name;
    private Integer student_class;
    private Integer student_roll_number;
}
