package at.spengergasse.valedyn.Exceptions;

public class NoFieldException extends Exception{

    public NoFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFieldException(Throwable cause) {
        super(cause);
    }

    public NoFieldException(String message) {
        super(message);
    }
}
