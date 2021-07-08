package com.example.farha_application.Models;

public class ContactModel {

    String name,number ;
    private String user_id , invitation_id, person_name;
    public ContactModel(String name, String number) {


        this.name = name;
        this.number = number;
    }

    public ContactModel(String name, String number, String user_id, String invitation_id, String person_name) {
        this.name = name;
        this.number = number;
        this.user_id = user_id;
        this.invitation_id = invitation_id;
        this.person_name = person_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", user_id='" + user_id + '\'' +
                ", invitation_id='" + invitation_id + '\'' +
                ", person_name='" + person_name + '\'' +
                '}';
    }
}
