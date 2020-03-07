<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ログイン</title>
	<!-- Bootstrap用CSSの読み込み -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/sunny/jquery-ui.css" >
	<script>
	 function checkNull(){
			var userId = document.getElementById("userId").value;
			var password = document.getElementById("password").value;
			if(userId && password){
				return true;
			}else{
				alert("入力してください。")
				return false;
			}
	 }
	</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="./index.jsp">家計簿</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
</nav>
<div class="container-fluid" style="padding-top:50px;">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form action="./LoginAccount" method="post" onsubmit="return checkNull()">
				<div class="form-group">
					<label for="content">ユーザーID</label>
					<input type="text" name="userId" class="form-control" id="userId" value="hoge">
				</div>
				<div class="form-group">
					<label for="priceAmount">パスワード</label>
					<input type="password" name="password" class="form-control" id="password" value="Hoge" />
				</div>
				<div align="center">
					<table style="width:100%;">
						<tr>
							<td align="left"><button type="submit" class="btn btn-primary">ログイン</button></td>
							<td align="right"><a href="./newAccount.jsp">新規会員登録</a></td>
						</tr>
					</table><br><br>
					<c:out value="${errorLogin}" />
				</div>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
</body>
</html>