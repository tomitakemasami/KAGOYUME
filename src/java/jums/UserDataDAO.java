
package jums;

import base.DBManager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * DBに関する処理のすべて
 *
 */
public class UserDataDAO implements Serializable {

    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    //カートに追加
    public void AddCart(String Code) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet data = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("select *from cart where ItemID=?");
            st.setString(1, Code);
            data = st.executeQuery();
            if (data.next()) {
                st = con.prepareStatement("update cart set ItemNumber=? where ItemID=?");
                st.setInt(1, data.getInt("ItemNumber") + 1);
                st.setString(2, Code);
                st.executeUpdate();
                Log.getInstance().LogWrite("処理AddCartを行いました。商品数は" + (data.getInt("ItemNumber") + 1) + "に増えました");
            } else {
                st = con.prepareStatement("insert into cart(ItemID,ItemNumber) values(?,?)");
                st.setString(1, Code);
                st.setInt(2, 1);
                st.executeUpdate();
                Log.getInstance().LogWrite("処理AddCartを行いました。商品数は一つです");
            }
            data.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
        }
    }

    //カートの中身を変更
    public void ChangeCart(String Code, String number) throws SQLException {
        int changenumber = Integer.parseInt(number);
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("update cart set ItemNumber=? where ItemID=?;");
            st.setInt(1, changenumber);
            st.setString(2, Code);
            st.executeUpdate();
            Log.getInstance().LogWrite("処理ChangeCartを行いました。商品数は"+changenumber+"つです");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
    }

    //カートの中身一つを削除
    public void DeleteCart(String code) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("delete from cart where ItemID = ? ;");
            st.setString(1, code);
            st.executeUpdate();
            Log.getInstance().LogWrite("処理DeleteCartを行いました。カート削除完了！");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
    }

    //カートの中身を全て削除
    public void DeleteCartAll() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("delete from cart  ;");
            st.executeUpdate();
            Log.getInstance().LogWrite("処理DeleteCartAllを行いました。カート全削除完了！");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
    }

    //カートの中身を確認
    public ArrayList<LookCartBeans> LookCart() throws SQLException {
        ArrayList<LookCartBeans> al = new ArrayList<LookCartBeans>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet data = null;
        try {
        con = DBManager.getConnection();
        st = con.prepareStatement("select *from cart");

        data = st.executeQuery();
        while (data.next()) {
            LookCartBeans lcb = new LookCartBeans();
            lcb.setCode(data.getString("ItemID"));
            lcb.setNumber(data.getInt("ItemNumber"));
            al.add(lcb);
        }
        Log.getInstance().LogWrite("処理LookCartを行いました。カート確認完了！");
        data.close();
        st.close();
        con.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
     return al; 
    }
    
    //名前とパスワードで検索し、userIDを返却
    public int LoginCheck(String name, String password) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet data = null;
        con = DBManager.getConnection();
        int userID = 0;
        try {
            st = con.prepareStatement("select *from user_t where name = ? and password = ? ;");

            st.setString(1, name);
            st.setString(2, password);
            data = st.executeQuery();
            if (data.next()) {
                userID = data.getInt("userID");
            }
            data.close();
            st.close();
            con.close();
            Log.getInstance().LogWrite("処理LoginCheckを行いました。ユーザーIDをsessionに保存します");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return userID;
    }

    //マイページの表示用に
    public UserDataDTO SearchMydata(Object user) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet data = null;
        con = DBManager.getConnection();
        UserDataDTO udd = new UserDataDTO();
        int userID = Integer.parseInt(user.toString());
        try {
            st = con.prepareStatement("select *from user_t where userID=?;");
            st.setInt(1, userID);
            data = st.executeQuery();
            if (data.next()) {
                udd.setName(data.getString("name"));
                udd.setPassWord(data.getString("password"));
                udd.setMail(data.getString("mail"));
                udd.setAddress(data.getString("address"));
                udd.setTotal(data.getInt("total"));
                udd.setNewDate(data.getTimestamp("newDate"));
            }
            data.close();
            st.close();
            con.close();
            Log.getInstance().LogWrite("処理SearchMydataを行いました。ユーザーデータを返却します");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return udd;
    }

    //新規登録
    public void UserInsert(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("insert into user_t("
                    + "name,password,mail,address,newDate)"
                    + " values(?,?,?,?,?)");

            st.setString(1, udd.getName());
            st.setString(2, udd.getPassWord());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();

            Log.getInstance().LogWrite("処理UserInsertを行いました。ユーザーデータを登録します");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //ユーザーデータ変更
    public void UserUpdate(UserDataDTO udd, Object user) throws SQLException {
        int userID = Integer.parseInt(user.toString());
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("update user_t set name=?,password=?,mail=?,address=? where userID=?;");

            st.setString(1, udd.getName());
            st.setString(2, udd.getPassWord());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setInt(5, userID);

            st.executeUpdate();

            Log.getInstance().LogWrite("処理UserUpdateを行いました。ユーザーデータを変更します");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //ユーザーデータ削除
    public void UserDelete(Object user) throws SQLException {
        int userID = Integer.parseInt(user.toString());
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("delete from user_t where userID = ?;");

            st.setInt(1, userID);

            st.executeUpdate();

            Log.getInstance().LogWrite("処理UserDeleteを行いました。ユーザーデータを削除します");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //DBに購入履歴を残す
    public void UserBuy(UserCartDTO ucd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        con = DBManager.getConnection();
        try {
            st = con.prepareStatement("insert into buy_t(userID,total,type,buyDate)values(?,?,?,?)");
            st.setInt(1, ucd.getUserID());
            st.setInt(2, ucd.getTotal());
            st.setInt(3, ucd.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            Log.getInstance().LogWrite("処理UserBuyを行いました。購入金額は"+ucd.getTotal()+"円です");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    //ユーザーの購入総額を残す
    public void UserTotal(UserCartDTO ucd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet data = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("select *from user_t where userID=?");
            st.setInt(1, ucd.getUserID());
            data = st.executeQuery();
            if (data.next()) {
                st = con.prepareStatement("update user_t set total=? where userID=?");
                st.setInt(1, data.getInt("total") + ucd.getTotal());
                st.setInt(2, ucd.getUserID());
                st.executeUpdate();
            }
            Log.getInstance().LogWrite("処理UserTotalを行いました。購入総額は"+data.getInt("total") + ucd.getTotal()+"円です");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
