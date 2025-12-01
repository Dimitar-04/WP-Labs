package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String name,String surname,String country,String biography){
        this.biography=biography;
        this.name=name;
        this.surname=surname;
        this.country=country;
        this.id=(long)(Math.random()*1000);
        this.books=new ArrayList<>();
    }

}
