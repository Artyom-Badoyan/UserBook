package com.example.userbook.servlets.book;

import com.example.userbook.models.Book;
import com.example.userbook.models.User;
import com.example.userbook.services.BookService;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "create_book", value = "/create_book")
public class CreateBookServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User logUser = (User) req.getSession().getAttribute("logUser");
        String name = req.getParameter("book_name");
        int page = Integer.parseInt(req.getParameter("book_page"));
        Date created = convertToDate(req.getParameter("book_date"));

        bookService.addBook(new Book(name, page, created), logUser);
        resp.sendRedirect("/UserBook_war_exploded/userHome");

    }

    @SneakyThrows
    private Date convertToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        return df.parse(dateStr);
    }
}
