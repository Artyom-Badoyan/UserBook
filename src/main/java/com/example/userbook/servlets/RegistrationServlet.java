package com.example.userbook.servlets;

import com.example.userbook.exceptions.UserAlreadyExistException;
import com.example.userbook.models.User;
import com.example.userbook.services.UserService;
import lombok.SneakyThrows;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {


    private UserService userService = new UserService();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("reg_email");
        String name = request.getParameter("reg_name");
        String surname = request.getParameter("reg_surname");
        String password = request.getParameter("reg_password");
        String fileName;
        try {
            Part filePart = request.getPart("user_image");
            fileName = System.currentTimeMillis() + "" + filePart.getSubmittedFileName();
            filePart.write("C:\\Users\\User\\Desktop\\usersImage\\" + fileName);
            User logUser = userService.save(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .imageName(fileName)
                    .build());
            request.getSession().setAttribute("logUser", logUser);
            response.sendRedirect("/UserBook_war_exploded/userHome");
        } catch (UserAlreadyExistException e) {
            request.setAttribute("welcomeMsg", "Welcome user book project");
            request.setAttribute("regErrorMessage", e.getErrorMessage());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

    }
}
