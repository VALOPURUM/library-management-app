package com.library.libarymanagement.librarymanagementapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.libarymanagement.librarymanagementapp.dto.AddBookRequest;
import com.library.libarymanagement.librarymanagementapp.dto.BookResponse;
import com.library.libarymanagement.librarymanagementapp.dto.GenericResponse;
import com.library.libarymanagement.librarymanagementapp.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Util {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public ResponseEntity<?> processResponse(GenericResponse response) {
        ResponseEntity<?> responseEntity = null;
        if (response == null) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (response.getStatusCode() == HttpStatus.OK.value()) {
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST.value()) {
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    public GenericResponse validateAddBookRequest(AddBookRequest request){
        GenericResponse response = null;
        if(request.getTitle().isEmpty()){
            response = new GenericResponse(400, "title is required!",null);
        }else if(request.getAuthor().isEmpty()){
            response =  new GenericResponse(400, "author is required!", null);
        }else if(request.getPublishedYear()==null){
            response = new GenericResponse(400, "publishedYear is required!", null);
        }
        return response;
    }

}
