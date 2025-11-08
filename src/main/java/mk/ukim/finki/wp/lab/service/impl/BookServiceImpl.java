package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exceptions.MissingBookFieldsException;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if(text==null||text.isEmpty()||rating==null){
            throw new IllegalArgumentException();
        }
        return bookRepository.searchBooks(text,rating);
    }

    @Override
    public Book addBook(String title, String genre, Double averageRating, Author author) {
        if (title==null||genre==null||averageRating==null||title.isEmpty()||genre.isEmpty()){
            throw new MissingBookFieldsException();
        }
        return bookRepository.addNewBook(title,genre,averageRating,author);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book editBook(Long id, String title, String genre, Double averageRating, Long authorId) {
        if(id==null||title.isEmpty()||genre.isEmpty()||averageRating==null||authorId==null){
            throw new MissingBookFieldsException();
        }
        Author author=authorRepository.findById(authorId);
        Book book=bookRepository.getById(id);
        book.setAverageRating(averageRating);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
