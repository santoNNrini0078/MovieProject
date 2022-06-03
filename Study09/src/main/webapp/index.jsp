<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>input태그의 전송방식</title>
	</head>
	<body>
		<div id="wrap">
			<form method="post" action="submit.jsp">
				<input type="button" value="버튼데이터" name="button"><br>
				<!-- 같은 name을 가진 input이 여러개 존재하는 경우 -->
				<input type="checkbox" value"체크박스1" name="checkbox">
				<input type="checkbox" value"체크박스2" name="checkbox">
				<input type="checkbox" value"체크박스3" name="checkbox">
				<fieldset>
					<legend>특이 태그들</legend>
					<input type="color" name="color"><br>
					<input type="date" name="date"><br>
					<input type="datetime-local" name="datetime"><br>
					<input type="range" name="range">
				</fieldset>
				<input type="submit" value="전송!" name="submit">
			</form>
			<!-- 특정 태그 input에 value, name이 있음에도 데이터가 전송되지 않는 경우가 존재한다. -->
			<!-- 이 경우 javascript를 이용하여 전송할 수 있다. -->
			
		</div>
	</body>
</html>