package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book>books=null;
    public static List<BookReservation>reservations=null;
    public static List<Author>authors=null;


    @PostConstruct
    public void init() {

        authors = new ArrayList<>();

        Author tolkien = new Author(
                "J.R.R.",
                "Tolkien",
                "United Kingdom",
                "English writer, philologist, and academic best known for 'The Hobbit' and 'The Lord of the Rings'."
        );

        Author orwell = new Author(
                "George",
                "Orwell",
                "United Kingdom",
                "English novelist, essayist, and critic, famous for his works exploring social injustice and totalitarianism."
        );

        Author austen = new Author(
                "Jane",
                "Austen",
                "United Kingdom",
                "English novelist known for her insightful portrayals of early 19th-century British society and romantic fiction."
        );

        authors.add(tolkien);
        authors.add(orwell);
        authors.add(austen);

        books = new ArrayList<>();
        books.add(new Book("The Hobbit", "Fantasy", 4.8, tolkien));
        books.add(new Book("The Lord of the Rings", "Epic Fantasy", 4.9, tolkien));
        books.add(new Book("The Silmarillion", "Mythic Fantasy", 4.7, tolkien));
        books.add(new Book("1984", "Dystopian / Political Fiction", 4.7, orwell));
        books.add(new Book("Animal Farm", "Political Satire / Allegory", 4.6, orwell));
        books.add(new Book("Homage to Catalonia", "Memoir / War", 4.3, orwell));
        books.add(new Book("Pride and Prejudice", "Romance / Classic", 4.8, austen));
        books.add(new Book("Sense and Sensibility", "Romance / Drama", 4.6, austen));
        books.add(new Book("Emma", "Romantic Comedy / Classic", 4.5, austen));
        books.add(new Book("Mansfield Park", "Classic / Drama", 4.4, austen));

        reservations = new ArrayList<>();
    }


}
