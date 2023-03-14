<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
    body {
        font-family: sans-serif;
    }
</style>
</head>
<body>
	<form action="/PIZZERIA/Login" method="post" align="center" style="margin-top:200px">
  
        <h2>Login Pizzeria</h2>

		<div>Username</div>
		<input type="text" name="username" /> 
		<br>
		<br>
		<div>Password</div>
		<input type="password" name="password" /> 
		<br>
		<br>
		<input type="submit" value="Login" />

	</form>

</body>
</html>