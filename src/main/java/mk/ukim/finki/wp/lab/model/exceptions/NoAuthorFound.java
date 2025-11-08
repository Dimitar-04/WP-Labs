package mk.ukim.finki.wp.lab.model.exceptions;

public class NoAuthorFound extends RuntimeException {
    public NoAuthorFound() {
        super("No author found");
    }
}
