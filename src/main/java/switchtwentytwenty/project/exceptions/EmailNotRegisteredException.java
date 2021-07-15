package switchtwentytwenty.project.exceptions;

public class EmailNotRegisteredException extends IllegalStateException{

    public EmailNotRegisteredException() {
        super("Email is not registered to any person");
    }
}
