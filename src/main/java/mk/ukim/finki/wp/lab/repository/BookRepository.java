package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book addNewBook(String title,String genre, Double rating, Author author);
    void deleteBook(Long id);

    Book getById(Long id);

    Book save(Book book);

}
