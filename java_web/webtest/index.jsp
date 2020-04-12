<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/java_web/CharacterEncodingTest">下载</a><br>
<form method = 'post' action='/java_web/RequestDemo'>
姓名：<input type='text' name='username'><br>
密码：<input type='password' name='pwd'><br>
<input type='submit'>
</form>
</br>
<a href="/java_web/RequestDemo2?username=中国">测试</a>
</body>
</html>