package dev.xenitane.studentmanagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "marks")
public class Marks implements Serializable {

    @EmbeddedId
    private MarksId marksId;

    @JsonProperty("student-id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JsonProperty("subject-id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @JsonProperty("marks")
    private Integer marks;

    public Marks(Student student, Subject subject, Integer marks) {
        this.marksId = new MarksId(student.getStudentId(), subject.getSubjectId());
        this.student = student;
        this.subject = subject;
        this.marks = marks;
    }
}
