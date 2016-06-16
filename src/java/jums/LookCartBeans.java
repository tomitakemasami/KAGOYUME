
package jums;

import java.io.Serializable;


public class LookCartBeans implements Serializable {

    private String Url;
    private String name;
    private String code;
    private int price;
    private String image;
    private String description;
    private int number;

    public LookCartBeans() {
        this.Url = "";
        this.name = "";
        this.price = 0;
        this.image = "";
        this.code = "";
        this.description = "";
        this.number = 0;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
