package switchtwentytwenty.project.exceptions;

public class InvalidPhoneNumberException extends IllegalStateException{


    public InvalidPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
