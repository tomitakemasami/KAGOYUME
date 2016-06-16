<%@page import="java.util.ArrayList"
        import="jums.LookCartBeans" 
        import="jums.JumsHelper"
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    
    /*Cartで格納したものを受け取る*/
    ArrayList<LookCartBeans> lcb = (ArrayList<LookCartBeans>) session.getAttribute("lcb");
    
    /*初期値を設定して変な動きを防止したい*/
    int total = 0;
    int j = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>buyconfirm</title>
    </head>
    <body>
        <div align="center">
        
            カート一覧<br>
            <table border="1">
                <tr>
                    <th>商品名</th>
                    <th>値段</th>
                    <th>画像</th>
                    <th>個数</th>
                    <th>合計金額</th>
                    <th></th>
                </tr>
                <tr>
                    <% for (int i = 0; i < lcb.size(); i++) {%>
                    <td width="120"><%= lcb.get(i).getName()%></td>
                    <td><%= lcb.get(i).getPrice()%>円</td>
                    <td><img src="<%= lcb.get(i).getImage()%>"></td>
                    <td width="30"><%= lcb.get(i).getNumber()%></td>
                    <td>合計<%= lcb.get(i).getPrice() * lcb.get(i).getNumber()%>円</td>
               <td width="110">
                   
                   
                   <form action="Change" method="GET">
                   
                  
                    <select name="number">                 
                        <% for (j = 1; j <= 1000; j++) {%>
              <option value="<%=j%>" <% if(lcb.get(i).getNumber() == j){out.print("selected = \"selected\"");}%>><%=j%></option>
                        <% }%>
                    </select>個
                    <input type="hidden" name="id" value="<%= lcb.get(i).getCode()%>">
                    <input type="submit" name="Submit" value="個数を変更する">
             　　　  </form>
                    <form action="Delete" method="GET">
                        <input type="hidden" name="id" value="<%= lcb.get(i).getCode()%>">
                        <input type="submit" name="Submit" value="カートから削除する">
                    </form>
    
                </tr>
                <%/*for文でpriceの合計をtotalに格納して表示、buycompleteで使用*/%>
                <% total = total + lcb.get(i).getPrice() * lcb.get(i).getNumber();%>
                <%}%>
            </table><form action="BuyComplete" method="POST"><br><%=total%>円<br>
            <input type="radio" name="type" value="1" checked>通常配送<br>
            <input type="radio" name="type" value="2">到着時刻指定配送<br>
            <input type="radio" name="type" value="3">お急ぎ便<br>
            <input type="radio" name="type" value="4">配送予約便<br>
            <input type="hidden" name="total" value="<%=total%>"><br>
            <input type="submit" name="btnSubmit" value="購入決定"><br>
            
            <%/*ここに遷移できる時点でログイン状態なので、他ページと違いif文がない*/%>
            <%=jh.LoginMode()%>
            <%=jh.top()%>
            <%=jh.cart()%>
        </form>
        </div>
    </body>
</html>
