package com.BookStore.Service;

import com.BookStore.Entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book createdBook(Book book);

    Page<Book> getAllBooks(String title, int page, int size, String sortBy);

    Book getBookById(Long id);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

}
