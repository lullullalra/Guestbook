<%@page import="java.util.Date"%>
<%@page import="gntp.lesson.guestbook.util.DateTimeService"%>
<%@page import="gntp.lesson.guestbook.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Update Book</title>
</head>
<body>
<h1>Update Book</h1>
<body>

<form action="update.do" method="post">
<%  GuestbookVO book = (GuestbookVO)request.getAttribute("book"); %>
<input type="hidden" name="seq" value="<%=book.getSeq()%>">
<table>
	<tr><td>title :</td><td><input type="text" name="title" value="<%=book.getTitle()%>"></td><td></td></tr>
	<tr><td>content :</td><td><input type="text" name="content" size="80" value="<%=book.getContent()%>"></td><td></td></tr>
	<tr><td>readCount :</td><td><input type="text" name="readCount" value="<%=book.getReadCount() %>" readonly="readonly"></td><td></td></tr>
	<tr><td>date :</td><td><input type="text" name="date" value="<%=DateTimeService.getDateTime(DateTimeService.DATE_TIME,new Date(book.getRegDate().getTime())) %>" readonly="readonly"></td><td></td></tr>
	<tr><td>userId :</td><td> <input type="text" name="userId" value="<%=book.getUserId() %>" readonly="readonly"></td><td></td></tr>
	<tr><td colspan="3"><input type="submit" value="글 수정"></td></tr>
</table>	
</form>
</body>
</html>