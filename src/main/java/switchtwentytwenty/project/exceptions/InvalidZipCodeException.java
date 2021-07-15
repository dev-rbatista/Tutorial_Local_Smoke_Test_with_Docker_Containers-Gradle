package switchtwentytwenty.project.exceptions;

public class InvalidZipCodeException extends IllegalStateException{

    public InvalidZipCodeException(String errorMessage) {
        super(errorMessage);
    }

}
