package switchtwentytwenty.project.domain.valueobject;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class RelationID implements ID, Serializable {
    private final int id;

    public RelationID(int relationID) {
        this.id = relationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationID)) return false;
        RelationID that = (RelationID) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}