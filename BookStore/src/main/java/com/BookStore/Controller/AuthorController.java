package com.BookStore.Controller;

import com.BookStore.Entity.Author;
import com.BookStore.Service.ServiceImpl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author APIs", description = "CRUD operations for managing authors in the bookstore")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl service;

    @Operation(summary = "Create a new author", description = "Adds a new author to the bookstore system.")
    @ApiResponse(responseCode = "201", description = "Author successfully created")
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author){
        Author created = service.createAuthor(author);
        return new  ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all authors", description = "Returns a list of all authors available in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched all authors.")
    @GetMapping
    public ResponseEntity<List<Author>> getAll(){
        return ResponseEntity.ok(service.getAllAuthors());
    }


    @Operation(summary = "Get author by ID", description = "Fetches a single author by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id){
        Author authorById = service.getAuthorById(id);
        return ResponseEntity.ok(authorById);

    }

    @Operation(summary = "Update an author", description = "Updates the name of an existing author by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author updated successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id,@RequestBody Author author){
        Author updated = service.updateAuthor(id, author);
        return ResponseEntity.ok(author);
    }

    @Operation(summary = "Delete an author", description = "Deletes an author permanently by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
