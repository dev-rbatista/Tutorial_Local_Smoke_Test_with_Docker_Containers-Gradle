package switchtwentytwenty.project.exceptions;

public class InvalidDateException extends IllegalArgumentException {
    public InvalidDateException(String s) {
        super(s);
    }
}