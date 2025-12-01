package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double averageRating;
    @ManyToOne
    @JoinColumn(name = "author.id")
    private Author author;

    public Book(String title,String genre,double averageRating,Author author){
        this.title=title;
        this.genre=genre;
        this.averageRating=averageRating;
        this.author=author;
        this.id=(long)(Math.random()*1000);
    }

}
