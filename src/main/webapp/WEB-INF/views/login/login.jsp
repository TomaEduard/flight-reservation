<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>

<body>

	<h2>Login:</h2>
	
	<form action="loginFromRegisterUser" method=GET>
	
		<pre>
		
			E-mail:<input type="text" name="email" />
			<br>
			Password:<input type="password" name="password" />
			<br>
			<input type="submit" value="login" />
			
		</pre>
		
		<br><br>
		
		<p>${msg}</p>
		
		
	</form>

</body>

</html>