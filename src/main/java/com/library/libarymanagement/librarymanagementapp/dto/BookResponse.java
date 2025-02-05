package com.library.libarymanagement.librarymanagementapp.dto;

import lombok.Data;

@Data
public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
}
