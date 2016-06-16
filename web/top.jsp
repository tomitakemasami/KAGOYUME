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
        <title>top</title>
    </head>
    <body>
        <div align="center"><marquee scrollamount="10000" truespeed direction="right" behavior="alternate" ><FONT size="8" color="green"><B>『かごいっぱいのゆめ』</B></FONT></marquee>
            <form action="Search" method="GET"><br>
                <marquee height="300" width="1000" behavior="alternate" direction="up" scrollamount="10" truespeed>
                    <div align="center"><h3>ショッピングサイトを使っている時、こんな経験ありませんか？<br>
            「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%オフセールだって！？　買わなきゃ！」・・・<br>
            ・・・そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。<br>
                    「こんなに買ってたのか・・・しょうがない。いくつか減らそう・・・」<br>
        仕方がありません。無駄遣いは厳禁です。でも、一度買うと決めたものを諦めるなんて、ストレスじゃあありませんか？<br>
                        できればお金の事なんか考えずに好きなだけ買い物がしたい・・・。<br>
                    このサービスは、そんなフラストレーションを解消するために生まれた<br>
            『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です！</h3></div></marquee><br><br><br>

                        検索フォームからキーワード検索が可能です。<br><br>
                    
                    <%if (request.getAttribute("error") != null){out.println(request.getAttribute("error"));}%><br><br>
                        <input type="text" name="name" placeholder="商品を検索"><br>
                        <input type="submit" name="and" value="検索"><br><br>
            </form>
                <%/*ログインの状態により表示を分岐*/%>
                <% if(session.getAttribute("userID") == null || session.getAttribute("userID").equals("0")){ %>
                <% out.print(jh.NotLoginMode());}else {out.print(jh.LoginMode());} %>
        
        </div>
    </body>
</html>
