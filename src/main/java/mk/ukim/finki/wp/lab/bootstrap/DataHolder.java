package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        // Skip if data already exists
        if (!authorRepository.findAll().isEmpty() && !bookRepository.findAll().isEmpty()) {
            authors = authorRepository.findAll();
            books = bookRepository.findAll();
            return;
        }

        // Create new authors without setting ID
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

        // Save authors and get managed entities
        authors = authorRepository.saveAll(List.of(tolkien, orwell, austen));

        // Use the saved (managed) entities
        Author savedTolkien = authors.get(0);
        Author savedOrwell = authors.get(1);
        Author savedAusten = authors.get(2);

        books = new ArrayList<>();
        books.add(new Book("The Hobbit", "Fantasy", 4.8, savedTolkien));
        books.add(new Book("The Lord of the Rings", "Epic Fantasy", 4.9, savedTolkien));
        books.add(new Book("The Silmarillion", "Mythic Fantasy", 4.7, savedTolkien));
        books.add(new Book("1984", "Dystopian / Political Fiction", 4.7, savedOrwell));
        books.add(new Book("Animal Farm", "Political Satire / Allegory", 4.6, savedOrwell));
        books.add(new Book("Homage to Catalonia", "Memoir / War", 4.3, savedOrwell));
        books.add(new Book("Pride and Prejudice", "Romance / Classic", 4.8, savedAusten));
        books.add(new Book("Sense and Sensibility", "Romance / Drama", 4.6, savedAusten));
        books.add(new Book("Emma", "Romantic Comedy / Classic", 4.5, savedAusten));
        books.add(new Book("Mansfield Park", "Classic / Drama", 4.4, savedAusten));

        books = bookRepository.saveAll(books);

        reservations = new ArrayList<>();
    }
}