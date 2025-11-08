package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String makeReservation(
            @RequestParam String readerName,
            @RequestParam String readerAddress,
            @RequestParam Integer numCopies,
            @RequestParam String chosenBook,
            HttpServletRequest request,
            Model model
    ){
        String ipAddress=request.getRemoteAddr();

        model.addAttribute("readerName",readerName);
        model.addAttribute("readerAddress",readerAddress);
        model.addAttribute("numCopies",numCopies);
        model.addAttribute("bookTitle",chosenBook);
        model.addAttribute("ip",ipAddress);
        try{
            bookReservationService.placeReservation(chosenBook,readerName,readerAddress,numCopies);
            return "reservationConfirmation";
        }catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());
            return "redirect:/books";
        }
    }

}
