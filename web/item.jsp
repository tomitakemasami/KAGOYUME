<%@page import="jums.LookCartBeans"
        import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    LookCartBeans srb = (LookCartBeans) request.getAttribute("srb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>item</title>
    </head>
    <body>
        <div align="center">
        <%/*「カートに追加」で遷移。「追加した」という一文を表示*/%>
     <%if (request.getAttribute("add") != null){out.println(request.getAttribute("add"));}%><br><br>
        URL:<%= srb.getUrl()%>
        <table border=1 width="1100">
            <tr>
                <th>商品名</th>
                <th>値段</th>
                <th>画像</th>
                <th>詳細説明</th>
                <th></th>
            </tr>
            
            <tr>
                <td width="150"><%= srb.getName()%></td>
                <td><%= srb.getPrice()%>円</td>
                <td><img src="<%= srb.getImage()%>"></td>
                <td><%= srb.getDescription()%></td>
                <td width="100"><a href="Add?id=<%= srb.getCode()%>">カートに追加</td>

            </tr>
        </table><br>
        <%/*ログインの状態より表示を分岐*/%>
     <% if (session.getAttribute("userID") == null || session.getAttribute("userID").equals("0")) {
              out.print(jh.NotLoginMode());
        } else {out.print(jh.LoginMode());}%>
        <%=jh.top()%>
        <%=jh.cart()%>
    </div>
    </body>
</html>
