package switchtwentytwenty.project.exceptions;

public class InvalidVATException extends IllegalStateException {

    public InvalidVATException(String errorMessage) {
        super(errorMessage);
    }
}
