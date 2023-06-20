package com.practice.myapp_vs.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.myapp_vs.entities.Book;
import com.practice.myapp_vs.services.BookService;



@RestController
public class TestController {

    @Autowired  
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>>getAllBooks(){

        List<Book> aBooks=bookService.getAllBooks();
        if (aBooks.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   
        }
        return ResponseEntity.of(Optional.of(aBooks));
    }

     @GetMapping("/book/{id}")
    public ResponseEntity<Book> getAllBookById(@PathVariable("id") int id){

        Book book= bookService.getBookById(id);
        if (book==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            
        }

        return ResponseEntity.of(Optional.of(book));

    }

    @PostMapping("/books")
    public ResponseEntity<Book> addbook(@RequestBody Book book) {
        //TODO: process POST request
        Book b=null;
        try {
           b=bookService.addBook(book);
        return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
         
    }

    @DeleteMapping("/books/{bid}")
    public void deleteABook(@PathVariable("bid") int bid){
        this.bookService.deleteABook(bid);
    }

    @PutMapping("/book/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId){
        this.bookService.updateBook(book, bookId);
        return book;
    }
    
}
