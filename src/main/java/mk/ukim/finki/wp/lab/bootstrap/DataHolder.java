package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book>books=null;
    public static List<BookReservation>reservations=null;

    @PostConstruct
    public void init(){
        books=new ArrayList<>();
        books.add(new Book("The Hobbit", "Fantasy", 4.8));
        books.add(new Book("To Kill a Mockingbird", "Classic / Drama", 4.9));
        books.add(new Book("1984", "Dystopian / Political Fiction", 4.7));
        books.add(new Book("The Great Gatsby", "Classic / Tragedy", 4.5));
        books.add(new Book("Harry Potter and the Sorcerer’s Stone", "Fantasy / Adventure", 4.9));
        books.add(new Book("Pride and Prejudice", "Romance / Classic", 4.8));
        books.add(new Book("The Catcher in the Rye", "Coming-of-Age / Drama", 4.2));
        books.add(new Book("The Lord of the Rings", "Epic Fantasy", 4.9));
        books.add(new Book("The Da Vinci Code", "Mystery / Thriller", 4.3));
        books.add(new Book("The Alchemist", "Philosophical / Adventure", 4.6));

        reservations=new ArrayList<>();
    }

}
