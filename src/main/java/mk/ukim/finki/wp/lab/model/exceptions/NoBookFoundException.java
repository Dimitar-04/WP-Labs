package mk.ukim.finki.wp.lab.model.exceptions;

public class NoBookFoundException extends RuntimeException{
    public NoBookFoundException(){
        super("No book found");
    }
}
