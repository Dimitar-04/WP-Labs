//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.model.exceptions.NoBookFoundException;
//import mk.ukim.finki.wp.lab.repository.AuthorRepository;
//import mk.ukim.finki.wp.lab.repository.BookRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryBookRepositoryImpl implements BookRepository {
//
//    private final AuthorRepository authorRepository;
//
//    public InMemoryBookRepositoryImpl(AuthorRepository authorRepository) {
//        this.authorRepository = authorRepository;
//    }
//
//    @Override
//    public List<Book> findAll() {
//        return DataHolder.books;
//    }
//
//    @Override
//    public List<Book> searchBooks(String text, Double rating) {
//        return DataHolder.books.stream().filter(b->b.getTitle().contains(text)&&b.getAverageRating()>=rating).toList();
//    }
//
//    @Override
//    public Book addNewBook(String title, String genre, Double rating, Author author) {
//        Book book=new Book(title,genre,rating,author);
//        DataHolder.books.add(book);
//        return book;
//    }
//
//    @Override
//    public void deleteBook(Long id) {
//        DataHolder.books.removeIf(b->b.getId().equals(id));
//    }
//
//    @Override
//    public Book getById(Long id) {
//        return DataHolder.books.stream().filter(b->b.getId().equals(id)).findFirst().orElseThrow(NoBookFoundException::new);
//    }
//
//    @Override
//    public Book save(Book book) {
//        DataHolder.books.removeIf(book1 -> book1.getId().equals(book.getId()));
//        DataHolder.books.add(book);
//
//
//        return book;
//    }
//}
