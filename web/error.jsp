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
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center">
        エラー
        <%if (request.getAttribute("error") != null){out.println(request.getAttribute("error"));}%><br><br>
        
        <%/*ログインの状態がセッションに入っており(LoginCheck.java)それにより表示を分岐させたい*/%>
     <% if (session.getAttribute("userID") == null || session.getAttribute("userID").equals("0")) {
              out.print(jh.NotLoginMode());
        } else {out.print(jh.LoginMode());}%>
        <%=jh.top()%>
        <%=jh.cart()%>
    </div>
    </body>
</html>
