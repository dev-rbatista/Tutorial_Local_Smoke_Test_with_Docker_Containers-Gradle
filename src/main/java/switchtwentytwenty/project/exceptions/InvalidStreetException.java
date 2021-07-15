package switchtwentytwenty.project.exceptions;

public class InvalidStreetException extends IllegalStateException {

    public InvalidStreetException(String errorMessage) {
        super(errorMessage);
    }
}
