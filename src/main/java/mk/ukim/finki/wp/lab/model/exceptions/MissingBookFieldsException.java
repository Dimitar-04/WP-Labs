package mk.ukim.finki.wp.lab.model.exceptions;

public class MissingBookFieldsException extends RuntimeException {
    public MissingBookFieldsException() {
        super("Cannot have missing fields!");
    }
}
