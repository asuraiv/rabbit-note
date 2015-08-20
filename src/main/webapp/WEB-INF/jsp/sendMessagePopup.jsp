<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${recipient}님께 쪽지 보내기</title>
<script type="text/javascript" src="${pageContext.request.contextPath}resources/javascript/lib/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#send").bind("click", send);
});

function send() {
	$.ajax({
		type : "get",
		url : "/sendMessage",
		data :$("#sendForm").serialize(),
		dataType : "json",
		success : function() {
			alert("쪽지를 보냈습니다");
			window.close();
		}
	});	
}
</script>
</head>
<body>
<form action="/sendMessage" id="sendForm">
	<input type="hidden" value="${sender}" name="sender">
	<input type="hidden" value="${recipient}" name="recipient">
	<textarea name="message" style="width: 375px; height: 150px;"></textarea>	
</form>
<button id="send">쪽지보내기</button>
</body>
</html>