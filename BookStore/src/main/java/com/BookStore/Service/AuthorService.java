package com.BookStore.Service;

import com.BookStore.Entity.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

}
