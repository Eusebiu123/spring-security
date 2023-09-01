package com.spring.api.controllers;

import com.spring.api.models.Book;
import com.spring.api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1")

@RestController
@CrossOrigin("http://localhost:3000")
public class BookController {

    @Autowired
    private BookService userService;
    @PostMapping("/newBook")
    Book newUser(@RequestBody Book newBook)
    {
        return userService.registerBook(newBook);
    }
    @GetMapping("/getBooks")
    List<Book> getAllUsers() {
        return userService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    Book getUserById(@PathVariable Long id) {
        return userService.getBookById(id);
    }

    @PutMapping("/book/{id}")
    Book updateUser(@RequestBody Book newBook, @PathVariable Long id) {
        return userService.updateBook(newBook,id);
    }

    @DeleteMapping("/book/{id}")
    String deleteUser(@PathVariable Long id){
        return userService.deleteBook(id);
    }
}
