<%@page import="java.util.ArrayList"
        import="jums.LookCartBeans" 
        import="jums.JumsHelper"
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<LookCartBeans> lcb = (ArrayList<LookCartBeans>) session.getAttribute("lcb");
    int j = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cart</title>
    </head>
    <body>
        <div align="center">
         <%if (request.getAttribute("add") != null){out.println(request.getAttribute("add"));}%><br><br>
         カート一覧<br>
        <BUTTON type="button" ><a href="BuyConfirm">購入</a></BUTTON>
        <table border=1  width="1100">
            <tr>
                <th>商品名</th>
                <th>値段</th>
                <th>画像</th>
                <th>詳細説明</th>
                <th>個数</th>
            </tr>
            <tr>
                <% for (int i = 0; i < lcb.size(); i++) {%>
                <td width="150"><a href="Item?id=<%= lcb.get(i).getCode()%>"><%= lcb.get(i).getName()%></a></td>
                <td><%= lcb.get(i).getPrice()%>円</td>
                <td><img src="<%= lcb.get(i).getImage()%>"></td>
                <td><%= lcb.get(i).getDescription()%></td>
                <td>
                    <form action="Change" method="GET">
                       <select name="number">                 
             <% for(j=1; j<=1000; j++) {%>
              <option value="<%=j%>" <% if(lcb.get(i).getNumber() == j){out.print("selected = \"selected\"");}%>><%=j%></option>
            <% } %>
                        </select>個<br>
                        <input type="hidden" name="id" value="<%= lcb.get(i).getCode()%>">
                        <input type="submit" name="Submit" value="個数を変更する">
                    </form>
                    <form action="Delete" method="GET">
                        <input type="hidden" name="id" value="<%= lcb.get(i).getCode()%>">
                        <input type="submit" name="Submit" value="カートから削除する">
                    </form>
            </tr>
            <%}%>
        </table><br>
          
                        <form action="Delete" method="GET">
                        <input type="hidden" name="id" value="all">
                        <input type="submit" name="Submit" value="カートの中身全てを削除する"><br><br>
                    </form>
        <button type="button" ><a href="BuyConfirm">購入確認画面へ</a></button><br><br>
        
        <%/*ログインの状態より表示を分岐*/%>
        <% if (session.getAttribute("userID") == null || session.getAttribute("userID").equals("0")) {
             out.print(jh.NotLoginMode());
         } else {
             out.print(jh.LoginMode());
         }%>
           <%=jh.top()%>
        </div>
    </body>
</html>
