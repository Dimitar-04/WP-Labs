package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/books","/"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;

        this.authorService = authorService;
    }

    @PostMapping
    public String getBooksByTitleAndRating(Model model,@RequestParam String bookTitle,@RequestParam Double rating){
        List<Book>books=bookService.searchBooks(bookTitle,rating);
        model.addAttribute("books",books);
        model.addAttribute("bookTitle",bookTitle);
        model.addAttribute("bookRating",rating);
        model.addAttribute("authors",authorService.findAll());
        model.addAttribute("bodyContent","listBooks");
        return "master-template";
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false)String error,@RequestParam(required = false)Long authorId, Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("error",error);
        }
        if (authorId != null) {
            model.addAttribute("selectedAuthorId",authorId);
            model.addAttribute("books", bookService.getByAuthorId(authorId));
        } else {
            model.addAttribute("books", bookService.listAll());

        }
        model.addAttribute("authors",authorService.findAll());
        model.addAttribute("bodyContent","listBooks");
        return "master-template";
    }

    @PostMapping("/searchByAuthor")
    public String getBooksByAuthor(@RequestParam(required = false)Long authorId,Model model){
        return "redirect:/books?authorId="+authorId;
    }

    @GetMapping("/add-new")
    @PreAuthorize("hasRole('ADMIN')")
    public String getBookForm(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "book-form";
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam (required = false) Double averageRating,
                           @RequestParam (required = false)Long authorId, Model model){
        Author author;
        try {
            author=authorService.findById(authorId);
            Book newBook=bookService.addBook(title,genre,averageRating,author);
            model.addAttribute("authors",authorService.findAll());
            return "redirect:/books";
        }catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());
            return "book-form";
        }


    }



    @GetMapping("/book-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditBookForm(@PathVariable Long id, Model model){
        try {
            Book book=bookService.getById(id);
            model.addAttribute("book",book);
            model.addAttribute("authors",authorService.findAll());
            return "book-form";
        }catch (RuntimeException e){
            return "redirect:/books?error=BookNotFound";
        }
    }

    @PostMapping("/edit/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam (required = false)Double averageRating,
                           @RequestParam  (required = false)Long authorId,
                           Model model){
        try{
            bookService.editBook(bookId,title,genre,averageRating,authorId);
            return "redirect:/books";
        }catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());
            Book book=bookService.getById(bookId);
            model.addAttribute("book",book);
            model.addAttribute("authors",authorService.findAll());
            return "book-form";
        }
    }

    @GetMapping("/delete-book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
