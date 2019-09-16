<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Flights</title>
</head>

<body>
	
		<h2>Find Flights:</h2>
		
		<form action="findFlights" method="POST" >
		
			Form:<input type="text" name="from" /> 
			To:<input type="text" name="to" /> 
			Departure Date:<input type="text" name="departureDate" /> 
			
			<input type="submit" value="search" />
		
		</form>

</body>

</html>