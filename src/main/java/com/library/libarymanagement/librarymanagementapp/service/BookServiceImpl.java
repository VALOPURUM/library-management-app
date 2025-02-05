package com.library.libarymanagement.librarymanagementapp.service;

import com.library.libarymanagement.librarymanagementapp.dto.AddBookRequest;
import com.library.libarymanagement.librarymanagementapp.dto.GenericResponse;
import com.library.libarymanagement.librarymanagementapp.dto.UpdateBookRequest;
import com.library.libarymanagement.librarymanagementapp.model.Book;
import com.library.libarymanagement.librarymanagementapp.repository.BookRepository;
import com.library.libarymanagement.librarymanagementapp.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final Util util;

    public BookServiceImpl(BookRepository bookRepository, Util util) {
        this.bookRepository = bookRepository;
        this.util = util;
    }

    // method for getting all books
    @Override
    public GenericResponse getAllBooks(Pageable pageable) {
        Page<Book> allBooks = bookRepository.findAll(pageable);
        if(!allBooks.getContent().isEmpty()) {
            return new GenericResponse(200, "success", allBooks);
        }else{
            return new GenericResponse(404, "Books not found", null);
        }
    }

    //Method for adding a new book
    @Override
    public GenericResponse addBook(AddBookRequest request) {
        GenericResponse response = util.validateAddBookRequest(request);
        if(response!=null){
            return response;
        }

        Optional<Book> existingBook = bookRepository.findByTitleAndAuthorAndIsbnAndPublishedYear(request.getTitle(),request.getAuthor(),request.getIsbn(),request.getPublishedYear());
        if(existingBook.isPresent()){
            return new GenericResponse(400,"The book you are trying to save, already exist!", null);
        }

        try {

            Book newBook = new Book();
            newBook.setTitle(request.getTitle());
            newBook.setAuthor(request.getAuthor());
            newBook.setIsbn(request.getIsbn());
            newBook.setPublishedYear(request.getPublishedYear());

            Book savedBook = bookRepository.save(newBook);
            return new GenericResponse(200, "Book saved successfully",savedBook);
        }catch (Exception e){
            log.error("An error occurred while trying to save Book to DB, message:{}",e.getMessage(),e);
            return new GenericResponse(500, "An error occurred, please try again later",null);

        }

    }

    // method for getting a book by ID
    @Override
    public GenericResponse getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book existingBook = optionalBook.get();
            return new GenericResponse(200, "success", existingBook);

        }else{
            return new GenericResponse(404, "Book not found", null);
        }

    }

    // method for updating a book by ID
    @Override
    public GenericResponse updateBook(Long id, UpdateBookRequest request) {

        if(id==null){
            return new GenericResponse(400,"id is required!",null);
        }
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            if(request.getTitle() != null &&!request.getTitle().isEmpty()) {
                existingBook.setTitle(request.getTitle());
            }
            if(request.getAuthor() != null &&!request.getAuthor().isEmpty()) {
                existingBook.setAuthor(request.getAuthor());
            }

            if(request.getIsbn() != null &&!request.getIsbn().isEmpty()) {
                existingBook.setIsbn(request.getIsbn());
            }
            if(request.getPublishedYear()!=null){
                existingBook.setPublishedYear(request.getPublishedYear());
            }
            return new GenericResponse(200, "Book updated successfully",existingBook);
        }else{
            return new GenericResponse(404, "Book with ID= "+id+ " not found", null);
        }
    }

    //method for deleting a book from the database
    @Override
    public GenericResponse deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            bookRepository.flush();

            return new GenericResponse(200, "Book deleted successfully",null);
        }catch (Exception e){
            log.error("An error occurred while trying to delete a book, message===>{}",e.getMessage(),e);
            return new GenericResponse(500, "An error occurred, please try again later", null);
        }
    }

    //method for filtering books by title or author
    @Override
    public GenericResponse filterBooksByTitleOrAuthor(String title, String author, Pageable pageable){

        GenericResponse filterResponse = null;
        if(author.isEmpty() && title.isEmpty()){
            filterResponse = new GenericResponse(400, "either author or title must be provided!", null);
        }
        if(!title.isEmpty() && author.isEmpty()){
            Page<Book> books = bookRepository.findByTitleAndAuthor(title, author, pageable);
            filterResponse= new GenericResponse(200, "success", books);
        }else if(!title.isEmpty()){
            Page<Book> books = bookRepository.findByTitle(title, pageable);
            filterResponse = new GenericResponse(200, "success", books);
        }else if(!author.isEmpty()){
            Page<Book> books = bookRepository.findByAuthor(author, pageable);
            filterResponse = new GenericResponse(200, "success", books);
        }
        return filterResponse;
    }
}
