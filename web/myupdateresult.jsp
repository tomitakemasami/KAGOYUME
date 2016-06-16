<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div align="center">
        変更が完了しました<br>
            <%=jh.Logout()%>
            <%=jh.top()%>
            <%=jh.cart()%>
        </div>
    </body>
</html>
