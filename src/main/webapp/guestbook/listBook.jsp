<%@page import="java.util.Date"%>
<%@page import="gntp.lesson.guestbook.util.DateTimeService"%>
<%@page import="gntp.lesson.guestbook.vo.GuestbookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guestbook list</title>
</head>
<body>
<h1>Guestbook List</h1>
<a href="viewWriteBook.do">방명록 작성</a>
<table>
<% 
	ArrayList<GuestbookVO> list = (ArrayList<GuestbookVO>)session.getAttribute("list");
	for(GuestbookVO book : list){
%>
	<tr>
		<td><%=book.getSeq() %></td>
		<td><%=book.getUserId() %></td>
		<td><a href="read.do?seq=<%=book.getSeq() %>&token=on"><%=book.getTitle() %></a></td>
		<td><%=book.getContent() %></td>
		<td><%=DateTimeService.getDateTime(DateTimeService.TIME_ONLY, new Date(book.getRegDate().getTime()))  %></td>
		<td><%=book.getReadCount() %></td>
		<td><a href="delete.do?seq=<%=book.getSeq() %>">삭제</a></td>
	</tr>
<% }%>	
</table>
</body>
</html>