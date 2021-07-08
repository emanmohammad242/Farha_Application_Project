package com.example.farha_application.Models;


import java.sql.*;

public class Invitation {
    private String id ;
    private String iduser ;
    private String title;
    private String description;
    private String final_date;
    private String first_date;
    private String type;

    public Invitation(String id, String iduser, String title, String description, String first_date , String final_date,String type) {
        this.id = id;
        this.iduser = iduser;
        this.title = title;
        this.description = description;
        this.first_date = first_date;
        this.final_date = final_date;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinal_date() {
        return final_date;
    }

    public void setFinal_date(String final_date) {
        final_date = final_date;
    }

    public String getFirst_date() {
        return first_date;
    }

    public void setFirst_date(String first_date) {
        this.first_date = first_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

