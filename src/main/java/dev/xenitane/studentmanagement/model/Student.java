package dev.xenitane.studentmanagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.yaml.snakeyaml.error.Mark;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Marks> marks = new HashSet<>();

    public void addOrUpdateMarks(Subject subject, Integer marks) {
        Stream<Marks> marksStream = this.marks.stream().filter(mark_i -> {
            return mark_i.getSubject().getSubjectId() == subject.getSubjectId();
        });
        if (marksStream.count() == 1) {
            marksStream.toList().get(0).setMarks(marks);
        } else if (marksStream.count() == 0) {
            this.marks.add(new Marks(this, subject, marks));
        } else {
            throw new IllegalArgumentException(
                    "There marks for this subjet for this student already exist. You might be interested on modification");
        }
    }
}
