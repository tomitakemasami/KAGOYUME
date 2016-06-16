<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mydeleteresult</title>
    </head>
    <body>
        <div align="center">
        削除されました
    
        <%=jh.top()%>
        <%=jh.cart()%>
        </div>
    </body>
</html>
