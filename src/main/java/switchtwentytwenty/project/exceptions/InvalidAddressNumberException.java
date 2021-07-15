package switchtwentytwenty.project.exceptions;

public class InvalidAddressNumberException extends IllegalStateException{

    public InvalidAddressNumberException(String errorMessage) {
        super(errorMessage);
    }
}
