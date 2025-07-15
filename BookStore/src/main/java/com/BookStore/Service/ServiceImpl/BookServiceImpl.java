package com.BookStore.Service.ServiceImpl;

import com.BookStore.Entity.Author;
import com.BookStore.Entity.Book;
import com.BookStore.Config.ResourceNotFoundException;
import com.BookStore.Repository.AuthorRepository;
import com.BookStore.Repository.BookRepository;
import com.BookStore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private AuthorRepository authorRepo;
    @Override
    public Book createdBook(Book book) {
        Long autherId=book.getAuthor().getId();
        Author author=authorRepo.findById(autherId).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    @Override
    public Page<Book> getAllBooks(String title, int page, int size, String sortBy) {
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return bookRepo.findByTitleContaining(title,pageable);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book Not Found"));
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existing=bookRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book Not found"));
        book.setId(id);
        return bookRepo.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book existing=bookRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book Not found"));
        bookRepo.deleteById(id);
    }
}
