//package mk.ukim.finki.wp.lab.repository.impl;
//
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.model.exceptions.NoAuthorFound;
//import mk.ukim.finki.wp.lab.repository.AuthorRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class AuthorRepositoryImpl implements AuthorRepository {
//
//    @Override
//    public List<Author> findAll() {
//        return DataHolder.authors;
//    }
//
//    @Override
//    public Author findById(Long id) {
//        return DataHolder.authors.stream().filter(a->a.getId().equals(id)).findFirst().orElseThrow(NoAuthorFound::new);
//    }
//}
