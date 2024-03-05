package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
@IdClass(StudentId.class)
public class Student implements Serializable {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @JsonProperty("name")
    @Column(nullable = false)
    private String studentName;

    @Id
    @JsonProperty("class")
    @Column(nullable = false)
    private Integer studentClass;

    @Id
    @Column(nullable = false)
    @JsonProperty("roll-number")
    private Integer studentRollNumber;
}
