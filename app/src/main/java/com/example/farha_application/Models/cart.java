package com.example.farha_application.Models;

public class cart {
    String 	id , product_id,	user_ids,	prodct_imageId,product_name, product_price,invitation_id;
    int token,remaining;

    public cart(String id, String product_id, String user_ids, String prodct_imageId, String product_name, String product_price,int token,int remaining,String invitation_id) {
        this.id = id;
        this.product_id = product_id;
        this.user_ids = user_ids;
        this.prodct_imageId = prodct_imageId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.token=token;
        this.remaining=remaining;
        this.invitation_id=invitation_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(String user_ids) {
        this.user_ids = user_ids;
    }

    public String getProdct_imageId() {
        return prodct_imageId;
    }

    public void setProdct_imageId(String prodct_imageId) {
        this.prodct_imageId = prodct_imageId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(String invitation_id) {
        this.invitation_id = invitation_id;
    }

    @Override
    public String toString() {
        return "cart{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", user_ids='" + user_ids + '\'' +
                ", prodct_imageId='" + prodct_imageId + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                '}';
    }
}
