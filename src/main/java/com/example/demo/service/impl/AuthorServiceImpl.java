package com.example.demo.service.impl;

import com.example.demo.error.AuthorDuplicateException;
import com.example.demo.error.AuthorNotFoundException;
import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthor(){
        return authorRepository.findAll();
    }

    @Override
    public Author newAuthor(@RequestBody Author newAuthors){
        if(authorRepository.existsByAuthorName(newAuthors.getAuthorName())){
            throw new AuthorDuplicateException(newAuthors.getAuthorName());
        } else {
            return authorRepository.save(newAuthors);
        }
    }

    @Override
    public Author findOneAuthor(@PathVariable Long id) {
        return authorRepository.findById(id).
        orElseThrow(()-> new AuthorNotFoundException(id));
    }

    @Override
    public Author Update(@RequestBody Author updateAuthor, @PathVariable Long id) {

        return authorRepository.findById(id)
                .map(x -> {
                    x.setAuthorName(updateAuthor.getAuthorName());
                    return authorRepository.save(x);
                })
                .orElseGet(() -> {
                    updateAuthor.setAuthorId(id);
                    return authorRepository.save(updateAuthor);
                });
    }

    @Override
    public void deleteAuthor(@PathVariable Long id){
        authorRepository.deleteById(id);
    }

}
