<%@page import="gntp.lesson.guestbook.vo.ReplyVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="gntp.lesson.guestbook.util.DateTimeService"%>
<%@page import="gntp.lesson.guestbook.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Book</title>
</head>
<body>
<h1>Read Book</h1>
<body>

<form action="writeReply.do" method="post">
<%  GuestbookVO book = (GuestbookVO)request.getAttribute("book"); %>
<input type="hidden" name="seq" value="<%=book.getSeq()%>">
<table>
	<tr><td>title :</td><td><input type="text" name="title" value="<%=book.getTitle()%>" readonly="readonly"></td><td></td></tr>
	<tr><td>content :</td><td><input type="text" name="content" size="80" value="<%=book.getContent()%>" readonly="readonly"></td><td></td></tr>
	<tr><td>readCount :</td><td><input type="text" name="readCount" value="<%=book.getReadCount() %>" readonly="readonly"></td><td></td></tr>
	<tr><td>date :</td><td><input type="text" name="date" value="<%=DateTimeService.getDateTime(DateTimeService.DATE_TIME,new Date(book.getRegDate().getTime())) %>" readonly="readonly"></td><td></td></tr>
	<tr><td>userId :</td><td> <input type="text" name="userId" value="<%=book.getUserId() %>" readonly="readonly"></td><td></td></tr>
	<!-- <tr><td colspan="3"><input type="submit" value="수정하기"></td></tr> -->
</table><br/>	
<!-- 댓글 목록 -->
<table>
<%  
	ArrayList<ReplyVO> list = book.getReplyList(); 
	if(list!=null){
		for(ReplyVO vo : list) {
%>
	<tr><td colspan="3"><%=vo.getReplySeq()+" "+vo.getContent()+" "+vo.getRegDate()%></td></tr>
<% } } %>	
</table>

<br/>
<table>
	<tr><td>댓글</td><td></td><td></td></tr>
	<tr><td colspan="3"><input type="text" name="reply" value="" size="100"></td></tr>
	<tr><td colspan="3"><input type="submit" value="수정하기"></td></tr>
</table>
</form>
</body>
</html>