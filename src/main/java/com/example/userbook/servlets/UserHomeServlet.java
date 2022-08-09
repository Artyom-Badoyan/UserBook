package com.example.userbook.servlets;

import com.example.userbook.models.User;
import com.example.userbook.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "userHome", value = "/userHome", loadOnStartup = 1)
public class UserHomeServlet extends HttpServlet {


    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("allBooks", bookService.allBooks());
        request.setAttribute("myBooks", bookService.byAuthor((User) request.getSession().getAttribute("logUser")));
        request.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(request, response);
    }
}
