package com.example.farha_application.Models;

public class category {
    private int num_ofProducts , id ;
    private String name_cat , image_name ;

    private String user_id , invitation_id;

    public category() { }

    public category(int id,String name_cat, String image_name, int num_ofProducts) {
        this.id = id;
        this.name_cat = name_cat;
        this.image_name = image_name;
        this.num_ofProducts = num_ofProducts;
    }

    public category(int id,String name_cat, String image_name, int num_ofProducts,  String user_id, String invitation_id) {
        this.name_cat = name_cat;
        this.image_name = image_name;
        this.num_ofProducts = num_ofProducts;
        this.id = id;
        this.user_id = user_id;
        this.invitation_id = invitation_id;

    }


    public category(int id, String name_cat, String image_name) {
        this.id = id;
        this.name_cat = name_cat;
        this.image_name = image_name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getNum_ofProducts() {
        return num_ofProducts;
    }

    public void setNum_ofProducts(int num_ofProducts) {
        this.num_ofProducts = num_ofProducts;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(String invitation_id) {
        this.invitation_id = invitation_id;
    }


    @Override
    public String toString() {
        return "category{" +
                "name_cat='" + name_cat + '\'' +
                ", image_name='" + image_name + '\'' +
                ", num_ofProducts=" + num_ofProducts +
                '}';
    }
}
