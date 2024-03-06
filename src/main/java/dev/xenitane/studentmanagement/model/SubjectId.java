package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SubjectId implements Serializable {
    private Long subjectId;
    private String subjectName;
}
