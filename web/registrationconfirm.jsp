<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserData" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = (UserData) hs.getAttribute("ud");
    ArrayList<String> chkList = ud.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>registrationconfirm</title>
    </head>
    <body>
        <div align="center">
        <% if (chkList.size() == 0) {%>
        <h1>登録確認</h1>
        名前:<%= ud.getName()%><br>
        パスワード：<%= ud.getPassWord()%><br>
        メールアドレス：<%= ud.getMail()%><br>
        住所：<%= ud.getAddress()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="RegistrationComplete" method="POST">
            <input type="submit" name="yes" value="はい">
        </form><br>
        <% } else {%>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList)%>
        <% }%>
        <form action="Registration" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
        </form><br>
        <%=jh.top()%>
        <%=jh.cart()%>
        </div>
    </body>
</html>
