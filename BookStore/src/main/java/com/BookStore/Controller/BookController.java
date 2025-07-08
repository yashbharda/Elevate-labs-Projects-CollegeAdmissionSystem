package com.BookStore.Controller;

import com.BookStore.Entity.Book;
import com.BookStore.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book APIs", description = "Operations related to books in the bookstore")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @Operation(summary = "Create a new book", description = "Adds a new book with an existing author ID")
    @ApiResponse(responseCode = "201", description = "Book successfully created")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book created = service.createdBook(book);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all books", description = "Returns a paginated and sorted list of books. You can also filter by title.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched all books.")
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "") String title,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy){
         Page<Book> books=service.getAllBooks(title, page, size, sortBy);
         return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get a book by ID", description = "Returns book details for a given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookById = service.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @Operation(summary = "Update a book", description = "Updates title, price, or author of an existing book by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book updated = service.updateBook(id, book);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a book", description = "Deletes a book permanently by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }



}
