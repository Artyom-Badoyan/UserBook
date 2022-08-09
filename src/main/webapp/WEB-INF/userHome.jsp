<%@ page import="com.example.userbook.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.07.2022
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>

</head>
<body>
<%User logUser =((User)request.getSession().getAttribute("logUser"));%>
<div>

    <h3>Welcome <%=logUser.getName()%> |  <%=logUser.getSurname() %></h3>
    <form action="logOut" method="post">
        <button>LogOut</button>
    </form>
</div>

<div class="createBook">
    <h2>Create Book</h2>
    <form action="create_book" method="post">
        <label for="book-name">Name:</label> <input type="text" name="book_name" id="book-name"/></br>
        <label for="book-page">Page:</label> <input type="number" name="book_page" id="book-page"/></br>
        <label for="book-date">Date:</label> <input type="date" name="book_date" id="book-date"/></br>
        <button>Create</button>
    </form>

</div>

<div class="allBooks">
    <h2>All books</h2>

    <table>
        <!-- here should go some titles... -->
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Page</th>
            <th>Author name</th>
        </tr>
        <c:forEach begin="0" end= "${ allBooks.size() }" step="1" varStatus="loopCounter" items="${allBooks}"
                    var="book">
            <tr>
                <td>
                    <c:out value="${book.id}" />
                </td>
                <td>
                    <c:out value="${book.name}" />
                </td>
                <td>
                    <c:out value="${book.page}" />
                </td>
                <td>
                    <c:out value="${book.author.name}" />
                </td>
                <td>
                    <c:out value="${book.created}" />
                </td>
            </tr>
        </c:forEach>
    </table>
    
</div>


<div class="myBooks">
    <h2>My Books</h2>
    <table>

        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Page</th>
            <th>Author name</th>
        </tr>
        <c:forEach begin="0" end= "${ myBooks.size() }" step="1" varStatus="loopCounter" items="${myBooks}"
                   var="book">
            <tr>
                <td>
                    <c:out value="${book.id}" />
                </td>
                <td>
                    <c:out value="${book.name}" />
                </td>
                <td>
                    <c:out value="${book.page}" />
                </td>
                <td>
                    <c:out value="${book.author.name}" />
                </td>
                <td>
                    <c:out value="${book.created}" />
                </td>
            </tr>
        </c:forEach>
    </table>

</div>



</body>
</html>
