
package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyUpdateResult extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
        HttpSession session = request.getSession();

        try {
            /*ページを戻った時に初期値として表示するように、入力されたデータをudに格納、
            空白の有無を判定、大丈夫ならDBにアクセス*/

            //フォームからの入力を取得して、JavaBeansに格納
            UserData ud = new UserData();
            ud.setName(request.getParameter("name"));
            ud.setPassWord(request.getParameter("password"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));
            UserDataDTO udd = new UserDataDTO();
            if (ud.chkproperties().size() > 0) {
                Log.getInstance().LogWrite("MyUpdateResultにアクセスしました。入力が不完全なのでmyupdate.jspにアクセスします");
                request.setAttribute("error", "入力が不完全です");
                request.getRequestDispatcher("/myupdate").forward(request, response);
            } else {
                Log.getInstance().LogWrite("MyUpdateResultにアクセスしました。myupdateresult.jspにアクセスします");
                UserDataDAO.getInstance().UserUpdate(ud.UD2DTOMapping(udd), session.getAttribute("userID"));
                
                /*sessionは削除*/
                session.removeAttribute("udd");
                session.removeAttribute("ud");
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
