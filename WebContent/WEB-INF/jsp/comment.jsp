<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br><br>
<form method='get'>
<input type="hidden" name="action" value="${action}">
Write a comment:<br><input type='text' name='comment'><br>
<button class="button"  type="submit">Comment</button>
</form>
</body>
</html>