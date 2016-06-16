<%@page import="javax.servlet.http.HttpSession"
        import="jums.UserData"
        import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData) session.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
        <div align="center">
        <form action="MyUpdateResult" method="POST">
            名前：<br>
            <input type="text" name="name" value="<%=ud.getName()%>">
            <br><br>
            パスワード：<br>
            <input type="text" name="password" value="<%=ud.getPassWord()%>">
            <br><br>
            メールアドレス：<br>
            <input type="text" name="mail" value="<%=ud.getMail()%>">
            <br><br>
            住所：
            <br>
            <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><%=ud.getAddress()%></textarea>
            <br><br>
            <input type="submit" name="btnSubmit" value="確認画面へ"><br><br>
            <input type="reset" value="リセット">
        </form>
            <%=jh.LoginMode()%>
            <%=jh.top()%>
            <%=jh.cart()%>
        </div>
    </body>
</html>
