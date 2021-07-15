package switchtwentytwenty.project.domain.valueobject;

import java.util.Objects;

public class Designation {

    private final String thisDesignation;


    public Designation(String thisDesignation) {
        validateDesignation(thisDesignation);
        this.thisDesignation = thisDesignation;
    }

    private void validateDesignation(String designation) {
        if(designation==null || designation.trim().length()==0){
            throw new IllegalArgumentException("Designation can't be null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designation that = (Designation) o;
        return Objects.equals(thisDesignation, that.thisDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thisDesignation);
    }

    public String toString(){
        return this.thisDesignation;
    }
}