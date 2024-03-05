package dev.xenitane.studentmanagement.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class StudentId implements Serializable {
    private Long studentId;
    private Integer studentClass;
    private Integer studentRollNumber;

    @Override
    public int hashCode() {
        return Objects.hash(this.studentId, this.studentClass, this.studentRollNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof StudentId))
            return false;
        StudentId other = (StudentId) o;
        return this.studentId == other.studentId && this.studentClass == other.studentClass
                && this.studentRollNumber == other.studentRollNumber;
    }
}
