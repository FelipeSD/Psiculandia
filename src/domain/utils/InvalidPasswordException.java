package domain.utils;

public class InvalidPasswordException extends Throwable {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
