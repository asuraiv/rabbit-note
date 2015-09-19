<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sender} 로부터 온 쪽지</title>
<script type="text/javascript" src="${pageContext.request.contextPath}resources/javascript/lib/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#reply").bind("click", reply);
});

function reply() {
	var recipient = "${sender}";
	// 메시지 보내기창으로 이동
	location.href = "/openSendPopup?sender=${sessionScope.userId }&recipient=" + recipient;
}
</script>
</head>
<body>
<textarea name="message" style="width: 375px; height: 150px;" readonly>${message}</textarea>
<button id="reply">답장하기</button>
</body>
</html>