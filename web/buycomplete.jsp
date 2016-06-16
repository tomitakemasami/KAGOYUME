<%@page import="jums.JumsHelper"
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>buycomplete</title>
    </head>
    <body>
        <div align="center">
        購入が完了しました<br>
        <%/*ここに遷移できる時点でログイン状態なので、他ページと違いif文がない*/%>
        <%=jh.LoginMode()%>
        <%=jh.top()%>
        <%=jh.cart()%>
        </div>
    </body>
</html>
