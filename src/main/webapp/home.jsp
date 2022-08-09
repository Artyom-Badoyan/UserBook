<%@ page import="com.example.userbook.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .registration {
            margin-left: auto;
            margin-left: 0;
            float: left;
        }

        .login {
            margin-left: auto;
            margin-right: 0;
            float: right;
        }
    </style>
</head>
<body>
<%
    Object user = request.getSession().getAttribute("logUser");
    if (user != null) {
//        String redirectUrl = "/WEB-INF/userHome.jsp";
//        request.getRequestDispatcher(redirectUrl).forward(request, response);
        response.sendRedirect("/UserBook_war_exploded/userHome");
    }

%>
<h1><%=request.getAttribute("welcomeMsg") %>
</h1>
<br/>

<div class="registration">
    <h2>Registration</h2>
<form action="registration" method="post" enctype="multipart/form-data">
    <label for="reg-name">Name:</label> <input type="text" name="reg_name" id="reg-name"/></br>
    <label for="reg-surname">Surname:</label> <input type="text" name="reg_surname" id="reg-surname"/></br>
    <label for="reg-email">Email:</label> <input type="text" name="reg_email" id="reg-email"/></br>
    <label for="reg-password">Password:</label> <input type="text" name="reg_password" id="reg-password"/></br>
    <label for="user_image">Upload Image:</label> <input type="file" name="user_image" id="user_image"/></br>
    <button>Registration</button>
    <c:if test="${regErrorMessage !=null}">
        <p style="color: red"><c:out value="${regErrorMessage}"/></p>
    </c:if>
</form>

</div>

<div class="login">
    <h2>Login</h2>

    <form action="login" method="post">
        <label for="login-email">Email:</label> <input type="text" name="login_email" id="login-email"/></br>
        <label for="login-email">Password:</label> <input type="password" name="login_password"
                                                          id="login-password"/>  </br>
        <c:if test="${errorMessage !=null}">
            <p style="color: red"><c:out value="${errorMessage}"/></p>
        </c:if>
        <button>Login</button>
    </form>
</div>
</body>
</html>