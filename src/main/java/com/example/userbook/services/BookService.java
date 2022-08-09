package com.example.userbook.services;

import com.example.userbook.models.Book;
import com.example.userbook.models.User;
import com.example.userbook.repositories.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository repository = new BookRepository();

    public Book addBook(Book newBook, User currentUser) {
        newBook.setAuthorId(currentUser.getId());
        newBook.setAuthor(currentUser);
        return repository.save(newBook);

    }

    public List<Book> allBooks(){
        return repository.allBooks();
    }

    public List<Book> byAuthor(User logUser) {
        return repository.getByAuthor(logUser);
    }
}
