<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>전송 확인</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("utf-8");
			String button=request.getParameter("button");
			String submit=request.getParameter("submit");
			//String checkbox=request.getParameter("checkbox");
			//같은 name을 가진 input의 값들은 최초로 전송된 하나의 값많을 getParameter로 가져올 수 있다.
			String[] checkbox=request.getParameterValues("checkbox");
			//request.getParameterValues(String); : 같은 이름을 가진 input들로부터 전송된 데이터를 배열로 추출
			
			//특이 input들의 값 확인
			String color=request.getParameter("color");//input type="color" #FFFFFF 형태로 전송된다.
			String date=request.getParameter("date");//input type="date" YYYY-DD형태로 전송된다.
			String datetime=request.getParameter("datetime");//YYYY-MM-DD 형태로 전송된다.
			String range=request.getParameter("range");
		%>
		<h1>button으로부터 전송 받은 값 : <%=button %></h1>
		<h1>submit으로부터 전송 받은 값 : <%=submit %></h1>
		<%
			for(int i=0;i<checkbox.length;i++)
			{
				%><h1>checkbox[<%=i %>]로부터 전송받은 값 : <%=checkbox[i]%></h1><%
			}
		%>
		<h1 style="color:<%=color %>;">color로부터 전송받은 값 : <%=color %></h1>
		<h1>date으로부터 전송 받은 값 : <%=date %></h1>
		<h1>datetime-local으로부터 전송 받은 값 : <%=datetime %></h1>
		<h1>range로부터 전송 받은 값 : <%=range %></h1>
	</body>
</html>