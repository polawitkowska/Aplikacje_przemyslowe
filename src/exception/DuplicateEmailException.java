package exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String email, boolean flag) {
        super("Employee with email " + email + " already exists");
    }
}

