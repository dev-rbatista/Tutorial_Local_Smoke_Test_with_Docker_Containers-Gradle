package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidNameException;

import java.util.Objects;

public class CategoryName implements ValueObject {

    private String name;

    public CategoryName(String name) {
        this.name = name;
        validation();
        trimData();
    }

    // UpperCase, requisito dado pelo PO (normalização do input dado)
    private void trimData() {
        this.name = this.name.trim().toUpperCase();
    }

    private void validation(){
        checkIfNotNull();
        checkIfNotEmpty();
    }

    private void checkIfNotNull(){
        if (this.name == null){
            throw new InvalidNameException("Name cannot be null");
        }
    }

    private void checkIfNotEmpty(){
        if (this.name.trim().length() == 0){
            throw new InvalidNameException("Name cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryName that = (CategoryName) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
