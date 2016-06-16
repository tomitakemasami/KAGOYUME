
package jums;

import java.io.Serializable;


public class SearchResultBeans implements Serializable {

    private String name;
    private String code;
    private String price;
    private String image;

    public SearchResultBeans() {

        this.name = "";
        this.price = "";
        this.image = "";
        this.code = "";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public void SRBtoLCBMapping(LookCartBeans lcb) {
        lcb.setName(this.name);
        lcb.setPrice(Integer.parseInt(this.price));
        lcb.setImage(this.image);
        lcb.setCode(this.code);

    }

}
