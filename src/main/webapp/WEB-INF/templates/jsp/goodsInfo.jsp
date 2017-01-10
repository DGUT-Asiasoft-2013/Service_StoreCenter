<%@ page language="java" contentType="text/html; charset=gb2312"%>  
<html>
<head>
<title>商品详情</title>
<style>
	h1, h3{
	text-align:center;}
</style>
</head>

<body>
<%  
  
String  title= new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8");  
String content = new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8");  
   
%>  
<h1><%=title %>	</h1>

<br />
<br />
<br />

<h3><b>商品详情</b>
	<%=content %>
</h3>
</body>
</html>