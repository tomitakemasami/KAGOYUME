<%@page import="javax.servlet.http.HttpSession"
         import="jums.UserData" 
         import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = null;
    boolean reinput = false;
    if (request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")) {
        reinput = true;
        ud = (UserData) hs.getAttribute("ud");
    }
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
        <form action="RegistrationConfirm" method="GET">
            名前：<br>
            <input type="text" name="name" value="<% if(reinput){out.print(ud.getName());}%>">
            <br><br>
            パスワード：<br>
            <input type="text" name="password" value="<% if(reinput){out.print(ud.getPassWord());}%>">
            <br><br>
            メールアドレス：<br>
            <input type="text" name="mail" value="<% if(reinput){out.print(ud.getMail());}%>">
            <br><br>
            住所：
            <br>
            <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><% if(reinput){out.print(ud.getAddress());}%></textarea>
            <br><br>
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form>
        <br>
    
            <%=jh.top()%>
            <%=jh.cart()%>
        </div>
    ]</body>
</html>
