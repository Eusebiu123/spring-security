package com.spring.api.services;


import com.spring.api.exception.UserNotFoundException;
import com.spring.api.models.Book;
import com.spring.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book registerBook(Book newBook){
        return bookRepository.save(newBook);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id)
    {
        return bookRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public Book updateBook(Book newBook, Long id)
    {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setPrice(newBook.getPrice());
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    return bookRepository.save(book);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteBook(Long id)
    {
        if(!bookRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return  "Book with id "+id+" has been deleted success.";
    }
}
