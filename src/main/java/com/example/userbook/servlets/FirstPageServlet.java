package com.example.userbook.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "firstPage", value = "/home", loadOnStartup = 1)
public class FirstPageServlet extends HttpServlet {
    private String message;

@Override
    public void init() {
        System.out.println("FirstPageServlet servlet initializated");
        message = "Welcome user book project! ";
    }
@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("logUser") != null) {
            response.sendRedirect("/UserBook_war_exploded/userHome");
        } else {
            request.setAttribute("welcomeMsg", message);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

    }

    public void destroy() {
    }
}