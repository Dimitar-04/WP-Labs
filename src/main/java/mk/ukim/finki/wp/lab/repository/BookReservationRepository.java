package mk.ukim.finki.wp.lab.repository;

import org.springframework.stereotype.Repository;

import mk.ukim.finki.wp.lab.model.BookReservation;


public interface BookReservationRepository{
    BookReservation save(BookReservation reservation);
}
