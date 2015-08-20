<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" 
		 contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Rabbit Note</title>
</head>
<body>
<h1>
	Hello RabbitNote
	<img alt="" src="http://www.anybuilder.co.kr/img_up/shop_pds/bverbuild/design/img/ico_s24_0/ico23.gif" style="width: 100px;">
</h1>

<br>
<br>

<form action="/login">
	<label>아이디를 입력하세요</label>&nbsp;
	<input type="text" name="userId">
	<input type="submit" value="입장">
</form>

<P> ${serverTime}. </P>
</body>
</html>
