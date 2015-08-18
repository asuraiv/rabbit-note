<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rabbit Note</title>
<script type="text/javascript" src="resources/javascript/lib/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

// queue 서버로부터 User List (메시지 수신함 리스트)를 가져온다
function pollUserList() {
	$.ajax({
		type : 'get',
		url : '/getUserList.nts',
		dataType : 'json',
		success : function(data) {			
			var userList = data.userList;			
			$('#userListDisp').empty();			
			// 사용자 목록 리스트업
			for(i = 0; i < userList.length; i++) {				
				if("${sessionScope.userId }" != userList[i]) {
					$('#userListDisp').append('<li>' + userList[i] + '</li>');
				}
			}			
			$('li').bind("click", openPopup);
		}		
	});
}

function pollGetMessage() {
	$.ajax({
		type : 'get',
		url : "/getMessage.nts?userId=${sessionScope.userId }",
		dataType : 'json',
		success : function(data) {
			console.debug(data);
			if(data == null) {
				return false;	
			}
			window.open("/openIncommingPopup.nts?sender=" + data.sender + "&message=" + data.message, "_blank",
			"width=400, height=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no");			
		}
	});
}

function openPopup() {
	var recipient = $(this).text();	
	window.open("/openSendPopup.nts?sender=${sessionScope.userId }&recipient=" + recipient, "_blank",
			"width=400, height=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no");
}

$(document).ready(function() {
	// 1초마다 발동
	setInterval(pollUserList, 1000);
	setInterval(pollGetMessage, 1000);
});
</script>

<style type="text/css">
li:hover {
	background-color: #D8F781;
	cursor: pointer;
}
#userListDisp {
	width : 100px;
}
</style>

</head>
<body>
${sessionScope.userId } 님 안녕하세요.

<!-- 로그인한 사용자 리스트 -->
<h4>사용자 목록</h4>
<div>
<ul id="userListDisp">
	
</ul>	
</div>
</body>
</html>