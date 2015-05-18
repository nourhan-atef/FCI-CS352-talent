<<<<<<< HEAD
<%@
page language="java" contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"
 %>
<%@
taglib prefix = "c"
uri="http://java.sun.com/jsp/jstl/core"
 %>
=======
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<<<<<<< HEAD
<p>welcome</p>
<c:forEach items="${it.list}" var="v">
<p><c:out value="${v}"> </c:out>  </p>
</c:forEach>
 <%System.out.println("hhhhhhhh"); %>
=======

<%@page
	import="java.net.URI , 
javax.ws.rs.client.Client ,
javax.ws.rs.client.ClientBuilder ,
javax.ws.rs.client.WebTarget ,
javax.ws.rs.core.MediaType ,
javax.ws.rs.core.Response ,
javax.ws.rs.core.UriBuilder ,
org.glassfish.jersey.client.ClientConfig"%>

<form action="/social/timeline_data" method="post">

<p> Welcome b2a ya ${it.postcontent} </p>
 </form>
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
</body>
</html>