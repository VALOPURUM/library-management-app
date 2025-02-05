package com.library.libarymanagement.librarymanagementapp.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library.libarymanagement.librarymanagementapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorAndIsbnAndPublishedYear(String title, String author, String isbn, Integer publishedYear);

    Page<Book> findByTitleAndAuthor(String title, String author, Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByAuthor(String author, Pageable pageable);
}
