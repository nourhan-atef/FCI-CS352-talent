<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

<%@page
	import="java.net.URI , 
javax.ws.rs.client.Client ,
javax.ws.rs.client.ClientBuilder ,
javax.ws.rs.client.WebTarget ,
javax.ws.rs.core.MediaType ,
javax.ws.rs.core.Response ,
javax.ws.rs.core.UriBuilder ,
org.glassfish.jersey.client.ClientConfig"%>

<form action="/social/hashtagtrends1" method="post">
<input type="submit" value="hashtagtrends">
<p> Welcome b2a ya ${it.postcontent} </p>
 </form>
</body>
</html>