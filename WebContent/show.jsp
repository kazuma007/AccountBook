<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<title>一覧</title>
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
			var userId = document.getElementById("email").value;
			if(userId){
				return true;
			}else{
				alert("メールアドレスを入力してください。")
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
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table align="center">
				<tr>
					<td align="center" style="border-style: none; width: 50px"><a href="./ShowAccountPreviousMonth">前月</a></td>
					<td align="center" style="border-style: none; width: 150px"><b><c:out value="${ym.year}" />/<c:out value="${ym.month}" />の出費</b></td>
					<td align="center" style="border-style: none; width: 50px"><a href="./ShowAccountNextMonth">次月</a></td>
			</table>
			<div align="center" style="padding-top:25px; padding-bottom:25px">
			合計金額:<b><c:out value="${al.total}" /></b>円
			</div>
			<c:if test="${fn:length(accountList) == 0}">
				<div align="center" style="padding-top: 50px">
				データがありません。
				</div>
			</c:if>
			<c:if test="${fn:length(accountList) > 0}">
			<canvas id="myPieChart"></canvas>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
			<script>
			var ctx = document.getElementById("myPieChart");
			var myPieChart = new Chart(ctx, {
				type: 'pie',
				data: {
					labels: ["食費", "日用品", "交際費", "交通費", "衣服費", "医療費", "趣味費", "その他"],
					datasets: [{
							backgroundColor: [
									"#E60012",
									"#F39800",
									"#FFF100",
									"#009944",
									"#0068B7",
									"#1D2088",
									"#920783",
									"#808000",
									"white"
							],
							data: [<c:out value="${al.food}" />, <c:out value="${al.dailyItem}" />,
								<c:out value="${al.friend}" />, <c:out value="${al.transport}" />, <c:out value="${al.cloth}" />,
									<c:out value="${al.hospital}" />, <c:out value="${al.hobby}" />, <c:out value="${al.other}" />]
					}]
				},
				options: {
					title: {
						display: true,
						text: '支出内訳'
					}
				}
			});
			</script>
			<div style="padding-top:50px;">
				<table class="table table-borderless">
					<thead class="thead-light">
						<tr>
							<th>No.</th>
							<th>日付</th>
							<th>カテゴリー</th>
							<th>金額</th>
							<th></th>
						</tr>
					</thead>
					<c:forEach var="list" items="${accountList}" varStatus="status">
					<tr>
						<td><c:out value="${status.count}" /></td>
						<td><c:out value="${list.date}" /></td>
						<td><c:out value="${list.category}" /></td>
						<td><c:out value="${list.amount}" /></td>
						<td><a href="./DetailAccount?pageId=<c:out value="${status.count - 1}" />"><button type="button" class="btn btn-primary rounded-pill">詳細</button></a></td>
					</tr>
					</c:forEach>
				</table>
		</div>
		<div align="center" style="padding-bottom:50px;">
			<p>下記アドレスに<c:out value="${ym.year}" />/<c:out value="${ym.month}" />の出費を送付！</p>
			<form class="form-inline justify-content-center" action="./SendAnEmail" method="post" onsubmit="return checkNull()">
				<div class="form-group">
				<input type="email" name="email" id="email" class="form-control" placeholder="Email">
				<input type="submit" class="form-control">
				</div>
			</form>
		</div>
		</c:if>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
</body>
</html>
