package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @JsonProperty("name")
    private String studentName;
    @JsonProperty("class")
    private Integer studentClass;
    @JsonProperty("roll-number")
    private Integer studentRollNumber;
}
