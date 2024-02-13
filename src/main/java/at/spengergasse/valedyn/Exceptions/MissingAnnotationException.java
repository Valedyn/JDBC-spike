package at.spengergasse.valedyn.Exceptions;

public class MissingAnnotationException extends Exception{
    public MissingAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingAnnotationException(Throwable cause) {
        super(cause);
    }

    public MissingAnnotationException(String message) {
        super(message);
    }
}
