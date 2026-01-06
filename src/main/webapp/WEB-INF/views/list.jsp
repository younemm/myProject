<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록(list)</title>
</head>
<body>
	<h3>회원목록</h3>
	<table border="1" width="700">
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>연락처</th>
			<th>성별</th>
			<th>취미</th>
			<th>가입일</th>
		</tr>
	<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.rno}</td>
			<td><a href="detail?rno=${dto.rno}">${dto.rid}</a></td>
			<td>${dto.rname}</td>
			<td>${dto.rtel}</td>
			<td>${dto.rgender}</td>
			<td>${dto.rhobby}</td>
			<td><fmt:formatDate value="${dto.rdate}" pattern="yyyy/MM/dd (E)" /></td>
		</tr>
	</c:forEach>	
	</table>
	<a href="writeForm">회원가입</a>
</body>
</html>