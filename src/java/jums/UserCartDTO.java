
package jums;

/**
 *buycomplete時にDBに購入履歴を作成するが、その時のためのJavaBeans
 * 
 */
public class UserCartDTO {

    private int buyID;
    private int userID;
    private int total;
    private int type;
    private int buyDate;

    public UserCartDTO() {

        this.buyID = 0;
        this.userID = 0;
        this.total = 0;
        this.type = 0;
        this.buyDate = 0;
    }

    public int getBuyID() {
        return buyID;
    }

    public void setBuyID(int buyID) {
        this.buyID = buyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(int buyDate) {
        this.buyDate = buyDate;
    }

}
