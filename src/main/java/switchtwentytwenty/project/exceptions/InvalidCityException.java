package switchtwentytwenty.project.exceptions;

public class InvalidCityException extends IllegalStateException{

    public InvalidCityException(String errorMessage) {
        super(errorMessage);
    }
}
