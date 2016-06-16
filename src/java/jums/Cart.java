
package jums;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Cart extends HttpServlet {

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
        ArrayList<LookCartBeans> AlLcb = new ArrayList<LookCartBeans>();
        try {
            /*SearchとItemで使用する情報を格納できるSearchResultBeansに、
            カートに入っている個数を追加したLookCartBeansを作成
            buyconfirm.jspでも使用するためsessionに入れる
             また、非ログイン状態でのアクセスに対してLoginへ飛ばし、非ログイン状態であることを知らせる*/
            
            Log.getInstance().LogWrite("Cartにアクセスされました。cart.jspに遷移します");
            for (int i = 0; i < UserDataDAO.getInstance().LookCart().size(); i++) {
                LookCartBeans lcb = new LookCartBeans();
                lcb=YahooConnect.getInstance().YahooSearchID(UserDataDAO.getInstance().LookCart().get(i).getCode());
                        
                lcb.setNumber(UserDataDAO.getInstance().LookCart().get(i).getNumber());
                AlLcb.add(lcb);
            }
            session.setAttribute("lcb", AlLcb);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
