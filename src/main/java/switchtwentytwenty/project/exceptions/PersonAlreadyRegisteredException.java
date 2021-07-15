package switchtwentytwenty.project.exceptions;

public class PersonAlreadyRegisteredException extends IllegalStateException{

    public PersonAlreadyRegisteredException(String message){
        super(message);
    }
}
