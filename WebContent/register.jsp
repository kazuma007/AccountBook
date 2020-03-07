<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf8");
String login = (String) session.getAttribute("loginStatus");
if (login == null || !login.equals("true")){
    pageContext.forward("./index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>登録</title>
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
	 $(function() {
	  $("#datepicker").datepicker({
		maxDate:0
	 });
	 });
	 jQuery(document).on('keydown', '.input_number_only', function(e){
	  let k = e.keyCode;
	  let str = String.fromCharCode(k);
	  if(!(str.match(/[0-9]/) || (37 <= k && k <= 40) || k === 8 || k === 46)){
	   return false;
	  }
	 });
	 jQuery(document).on('keyup', '.input_number_only', function(e){
	  this.value = this.value.replace(/[^0-9]+/i,'');
	 });
	 jQuery(document).on('blur', '.input_number_only',function(){
	  this.value = this.value.replace(/[^0-9]+/i,'');
	 });

	 function checkNull(){
			var date = document.getElementById("datepicker").value;
			var category = document.getElementById("category").value;
			var content = document.getElementById("content").value;
			var amount = document.getElementById("amount").value;
			if(date && category && content && amount){
				return true;
			}else{
				alert("入力してね。")
				return false;
			}
	 }
	</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="./register.jsp">家計簿</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="./register.jsp">登録<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="./ShowAccount">一覧</a>
			</li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" href="./LogoutAccount">ログアウト<span class="sr-only"></span></a>
			</li>
		</ul>
	</div>
</nav>
<div class="container-fluid" style="padding-top:50px;">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form action="./InsertAccount" method="post" onsubmit="return checkNull()">
				<div class="form-group">
					<label for="date">日付</label>
					<input type="text" name="date" class="form-control" id="datepicker" readonly>
				</div>
				<div class="form-group">
					<label for="category">カテゴリー</label>
					<select class="form-control" name="category" id="category">
						<option>食費</option>
						<option>日用品</option>
						<option>交際費</option>
						<option>交通費</option>
						<option>衣服費</option>
						<option>医療費</option>
						<option>趣味費</option>
						<option>その他</option>
					</select>
				</div>
				<div class="form-group">
					<label for="content">内容</label>
					<input type="text" name="content" class="form-control" id="content">
				</div>
				<div class="form-group">
					<label for="priceAmount">金額</label>
					<input type="text" name="priceAmount" class="form-control input_number_only" id="amount" />
				</div>
				<div align="center">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
</body>
</html>