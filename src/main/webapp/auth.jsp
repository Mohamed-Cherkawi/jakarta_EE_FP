<%--
  Created by IntelliJ IDEA.
  User: email
  Date: 10/20/2022
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body style=" display: flex; justify-content: center; align-items: center; background-color: antiquewhite">
<div>
    <form method="POST" action="Login">
        <h3 style="width: 100%">Login Form</h3>
        <div style="margin-bottom: 2rem; margin-top: 2rem"><input type="text" placeholder="Enter username" name="username" required></div>
        <div style="margin-bottom: 2rem; margin-top: 2rem"><input type="password" placeholder="Enter password" name="pwd" required></div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
