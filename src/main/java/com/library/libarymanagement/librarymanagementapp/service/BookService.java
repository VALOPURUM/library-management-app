package com.library.libarymanagement.librarymanagementapp.service;

import com.library.libarymanagement.librarymanagementapp.dto.AddBookRequest;
import com.library.libarymanagement.librarymanagementapp.dto.GenericResponse;
import com.library.libarymanagement.librarymanagementapp.dto.SearchRequest;
import com.library.libarymanagement.librarymanagementapp.dto.UpdateBookRequest;
import com.library.libarymanagement.librarymanagementapp.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    GenericResponse getAllBooks(Pageable pageable);

    GenericResponse addBook(AddBookRequest request);

    GenericResponse getBookById(Long id);

    GenericResponse deleteBook(Long id);

    GenericResponse updateBook(Long id, UpdateBookRequest request);

    //method for riltering books by title or author
    GenericResponse filterBooksByTitleOrAuthor(String title, String author, Pageable pageable);
}
