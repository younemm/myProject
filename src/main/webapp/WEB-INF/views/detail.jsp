<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 상세보기(detail)</title>
</head>
<body>
	<h3>회원정보 상세보기</h3>
	<table border="1" width="500">
		<tr>
			<th>번호</th>
			<td>${detail.rno}</td>
			<th>아이디</th>
			<td>${detail.rid}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${detail.rname}</td>
			<th>연락처</th>
			<td>${detail.rtel}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${detail.rgender}</td>
			<th>취미</th>
			<td>${detail.rhobby}</td>
		</tr>
		<tr>
			<td colspan="4">${detail.rprofile}</td>
		</tr>
		<tr>
			<td colspan="4"> 
			<a href="passwordCheckForm?rno=${detail.rno}&mode=update"> 회원정보수정 </a> | 
			<a href="passwordCheckForm?rno=${detail.rno}&mode=delete"> 회원탈퇴 </a></td>
		</tr>
	</table>
</body>
</html>