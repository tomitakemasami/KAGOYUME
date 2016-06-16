
package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            /* 「カートに追加」から遷移。
            各追加ボタンからrequestで商品コードを飛ばしてカートに追加する。
            「カートに追加」を連続で押した場合request.getHeader("REFERER")がこのページ自身に再帰しエラー発生するため
            if文によりAddにアクセスするであろう３ページ分の分岐を用意*/
            
            UserDataDAO.getInstance().AddCart(request.getParameter("id"));
            request.setAttribute("add", "カートに追加しました");
            Log.getInstance().LogWrite("Addにアクセスされました。商品コード" + request.getParameter("id") + "を追加しました");
            if ("/Add".equals(request.getHeader("REFERER").substring(30, 34))) {
                Log.getInstance().LogWrite("連続でAddされました。記録された遷移元" + session.getAttribute("ad") + "に戻ります");
                request.getRequestDispatcher(session.getAttribute("ad").toString()).forward(request, response);

            } else if ("/Search".equals(request.getHeader("REFERER").substring(30, 37))) {
                Log.getInstance().LogWrite("連続でAddされました。記録された遷移元" + request.getAttribute("ad") + "に戻ります");
                session.setAttribute("ad", request.getHeader("REFERER").substring(30));
                request.getRequestDispatcher(request.getHeader("REFERER").substring(30)).forward(request, response);

            } else if ("/Item".equals(request.getHeader("REFERER").substring(30, 35))) {
                Log.getInstance().LogWrite("連続でAddされました。記録された遷移元" + request.getAttribute("ad") + "に戻ります");
                session.setAttribute("ad", request.getHeader("REFERER").substring(30));
                request.getRequestDispatcher(request.getHeader("REFERER").substring(30)).forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
