package com.BookStore.Service.ServiceImpl;

import com.BookStore.Entity.Author;
import com.BookStore.Config.ResourceNotFoundException;
import com.BookStore.Repository.AuthorRepository;
import com.BookStore.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Author "+id+" is not found"));
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author existing=authorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Author "+id+" is not found"));
        author.setId(id);
        return authorRepo.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author existing=authorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Author "+id+" is not found"));
        authorRepo.deleteById(id);
    }
}
