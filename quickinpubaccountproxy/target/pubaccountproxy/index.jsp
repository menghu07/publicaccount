<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<form method="post" action="/pubaccountproxy/wxmessage">
    echostr: <input name="echostr"/>
    timestamp: <input name="timestamp"/>
    signature: <input name="signature"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
