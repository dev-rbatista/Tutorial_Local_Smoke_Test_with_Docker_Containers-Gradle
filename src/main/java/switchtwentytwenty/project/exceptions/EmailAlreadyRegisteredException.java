package switchtwentytwenty.project.exceptions;

public class EmailAlreadyRegisteredException extends IllegalStateException{

    public EmailAlreadyRegisteredException() {
        super("Email is already registered");
    }
}