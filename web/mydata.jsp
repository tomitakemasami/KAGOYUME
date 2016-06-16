<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserDataDTO udd = (UserDataDTO) session.getAttribute("udd");
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
            
            <%/*userIDは処理の判定で使用するため表示させない-*/%>
        ユーザーデータ詳細<br>
        名前:<%= udd.getName()%><br>
        パスワード：<%= udd.getPassWord()%><br>
        メールアドレス：<%= udd.getMail()%><br>
        住所：<%= udd.getAddress()%><br>
        累計購入金額：<%= udd.getTotal()%><br>
        登録日時：<%= udd.getNewDate()%><br>
        <button type="button" ><a href="MyUpdate">変更</a></button><br><br>
        <button type="button" ><a href="MyDelete">削除</a></button><br><br>
            <%=jh.Logout()%>
            <%=jh.top()%>
            <%=jh.cart()%>
        </div></body>
</html>
