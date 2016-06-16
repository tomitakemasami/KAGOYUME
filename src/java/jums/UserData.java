
package jums;

import java.util.ArrayList;

/**
 *ユーザー新規登録の入力フォームに対応するJavaBeans
 *UserDataDTO用に相互マッピング機能を備える
 */
public class UserData {

    private String name;
    private String password;
    private String mail;
    private String address;

    public UserData() {
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if (name.trim().length() == 0) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String password) {
        //空文字(未入力)の場合空文字をセット
        if (password.trim().length() == 0) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        //空文字(未入力)の場合空文字をセット
        if (name.trim().length() == 0) {
            this.mail = "";
        } else {
            this.mail = mail;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        //空文字(未入力)の場合空文字をセット
        if (address.trim().length() == 0) {
            this.address = "";
        } else {
            this.address = address;
        }
    }

    public ArrayList<String> chkproperties() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.name.equals("")) {
            chkList.add("name");
        }

        if (this.password.equals("")) {
            chkList.add("password");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }

        return chkList;
    }

    public UserDataDTO UD2DTOMapping(UserDataDTO udd) {
        udd.setName(this.name);
        udd.setPassWord(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        return udd;
    }

    public void DTO2UDMapping(UserDataDTO udd) {
        this.setName(udd.getName());
        this.setPassWord(udd.getPassWord());
        this.setMail(udd.getMail());
        this.setAddress(udd.getAddress());
    }
}
