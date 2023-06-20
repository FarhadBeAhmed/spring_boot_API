package com.practice.myapp_vs.services;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.practice.myapp_vs.entities.Book;


@Component
public class BookService {
    private static List<Book> bookList=new ArrayList<>();

    static{
        bookList.add(new Book(12,"java","Java is a good programming language book"));
        bookList.add(new Book(13,"dart","dart is a good programming language book"));
        bookList.add(new Book(14,"kotlin","kotlin is a good programming language book"));
    }

    public List<Book> getAllBooks(){
        return bookList;
    }
    public Book getBookById(int id){
        Book book= bookList.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }


    public Book addBook(Book b){
        bookList.add(b);
        return b;
    }

    public void deleteABook(int bid){
        bookList=bookList.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());

    }

    public void updateBook(Book book,int bookId){
        bookList.stream().map(b -> {
            if (b.getId()==bookId) {
                b.setName(book.getName());
                b.setDescription(book.getDescription());
            }
            return b;
        }).collect(Collectors.toList());
    }

}
