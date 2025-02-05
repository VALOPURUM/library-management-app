package com.library.libarymanagement.librarymanagementapp.controller;



import com.library.libarymanagement.librarymanagementapp.dto.AddBookRequest;
import com.library.libarymanagement.librarymanagementapp.dto.GenericResponse;
import com.library.libarymanagement.librarymanagementapp.dto.UpdateBookRequest;
import com.library.libarymanagement.librarymanagementapp.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.libarymanagement.librarymanagementapp.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final Util util;


    public BookController(BookService bookService, Util util) {
        this.bookService = bookService;
        this.util = util;
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest addBookRequest) {
        GenericResponse response = bookService.addBook(addBookRequest);
        return util.processResponse(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(Pageable pageable) {
        GenericResponse response = bookService.getAllBooks(pageable);
        return util.processResponse(response);

    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        GenericResponse response = bookService.getBookById(bookId);
        return util.processResponse(response);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody UpdateBookRequest request) {
        GenericResponse response = bookService.updateBook(bookId, request);
        return util.processResponse(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        GenericResponse response = bookService.deleteBook(id);
        return util.processResponse(response);
    }

    @GetMapping("/searchBooksByTitleOrAuthor/{author}/{title}")
    public ResponseEntity<?> searchBooksByTitleOrAuthor(@PathVariable String author, @PathVariable String title, Pageable pageable) {
        GenericResponse response = bookService.filterBooksByTitleOrAuthor(title,author, pageable);
        return util.processResponse(response);

    }
}
