package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;

        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false)String error, Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("error",error);
        }
        model.addAttribute("books",bookService.listAll());
        return "listBooks";
    }

    @GetMapping("/add-new")
    public String getBookForm(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "book-form";
    }


    @PostMapping("/add")
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
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
