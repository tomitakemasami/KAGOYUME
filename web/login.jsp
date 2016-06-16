<%@page import="jums.JumsHelper"%>

<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <div align="center">
        <form action="LoginCheck" method="POST">
            <h1>ユーザー情報管理トップ</h1><br>
            <h3>ここでは、ユーザー情報管理システムとして<br>
                ユーザー情報の登録、<br>
                修正や削除を行うことができます</h3><br>
            <%if (request.getAttribute("error") != null){out.println(request.getAttribute("error"));}%><br><br>
            名前：<br>
            <input type="text" name="name"><br>
            パスワード：<br>
            <input type="text" name="password"><br>
            <input type="submit" name="Submit" value="ログイン"><br><br>
            <br><br>
            <button type="button" ><a href="Registration">新規登録</a></button><br>
        </form>
            <%=jh.top()%>
            <%=jh.cart()%>
       </div>
    </body>
</html>
