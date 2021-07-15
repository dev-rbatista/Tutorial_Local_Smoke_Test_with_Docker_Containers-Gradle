package switchtwentytwenty.project.exceptions;

public class AccountAlreadyRegisteredException extends IllegalArgumentException {

    public AccountAlreadyRegisteredException() {
        super ("Account is already registered");
    }
}