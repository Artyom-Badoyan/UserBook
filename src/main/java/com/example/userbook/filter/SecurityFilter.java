package com.example.userbook.filter;

import com.example.userbook.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    List<String> secureUrls = Arrays.asList("/userHome", "/create_book");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isSecure(request.getRequestURL().toString())) {
            User logUser = (User) request.getSession().getAttribute("logUser");
            if (logUser == null) {
                System.out.println("No session");
                ((HttpServletResponse) servletResponse).sendRedirect("/UserBook_war_exploded/home");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }


    private boolean isSecure(String url){
        for (String secureUrl : secureUrls) {
            if(url.contains(secureUrl)){
                return true;
            }
        }
        return false;
    }
}
