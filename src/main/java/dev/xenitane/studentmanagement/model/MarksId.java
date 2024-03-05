package dev.xenitane.studentmanagement.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class MarksId implements Serializable {
    private Long studentId;
    private Long subjectId;

    @Override
    public int hashCode() {
        return Objects.hash(this.studentId, this.subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof MarksId))
            return false;
        MarksId other = (MarksId) o;
        return other.studentId == this.studentId && other.subjectId == this.subjectId;
    }
}
