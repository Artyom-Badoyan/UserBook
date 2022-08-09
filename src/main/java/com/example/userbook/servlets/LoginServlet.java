package com.example.userbook.servlets;

import com.example.userbook.exceptions.UserNotFoundException;
import com.example.userbook.models.User;
import com.example.userbook.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
    private String message;
    private UserService userService;

    public void init() {
        message = "Welcome user book project";
        userService = new UserService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("logUser") != null) {
            response.sendRedirect("/UserBook_war_exploded/userHome");
            return;
        }
        String email = request.getParameter("login_email");
        String password = request.getParameter("login_password");
        try {
            User logUser = userService.getByEmailAndPassword(email, password);
            request.getSession().setAttribute("logUser", logUser);
            response.sendRedirect("/UserBook_war_exploded/userHome");
        } catch (UserNotFoundException e) {
            request.setAttribute("welcomeMsg", message);
            request.setAttribute("errorMessage", e.getErrorMessage());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

    }

    public void destroy() {
    }
}