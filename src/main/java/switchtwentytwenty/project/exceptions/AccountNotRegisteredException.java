package switchtwentytwenty.project.exceptions;

public class AccountNotRegisteredException extends IllegalStateException{

    public AccountNotRegisteredException() {
        super("Account not registered");
    }
}
