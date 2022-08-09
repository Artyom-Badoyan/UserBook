package com.example.userbook.repositories;

import com.example.userbook.configs.db.DBConnectionProvider;
import com.example.userbook.models.Book;
import com.example.userbook.models.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();


    private UserRepository userRepository = new UserRepository();

    @SneakyThrows
    public Book save(Book book) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO books (`name`, created, author_id, page) values(?,?,?,? )",
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setDate(2, new Date(book.getCreated().getTime()));
        preparedStatement.setInt(3, book.getAuthorId());
        preparedStatement.setInt(4, book.getPage());
        int execute = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int id = generatedKeys.getInt(1);
        book.setId(id);
        if (execute > 0) {
            System.out.println("new book added: " + book);
        }
        return book;

    }

    @SneakyThrows
    public List<Book> getByAuthor(User author) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from books b where b.author_id = ?");
        preparedStatement.setInt(1, author.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();

            book.setId(resultSet.getInt("id"));
            book.setAuthor(author);
            book.setAuthorId(author.getId());
            book.setName(resultSet.getString("name"));
            book.setPage(resultSet.getInt("page"));
            book.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));

            books.add(book);
        }
        return books;
    }

    @SneakyThrows
    public List<Book> allBooks() {
        List<Book> books = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from books");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setPage(resultSet.getInt("page"));
            book.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));
            book.setAuthor(userRepository.getById(resultSet.getInt("author_id")));
            books.add(book);
        }
        return books;
    }
}
