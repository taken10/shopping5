<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 評価結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/shop/contents/css/shopping.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1>評価を承りました</h1>
		<p>ありがとうございます！<img src="/shop/contents/img/Thankyou.jpg" width="100" height="100"/></p>
		<form action="./ShoppingServlet_ans" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
	</body>
</html>