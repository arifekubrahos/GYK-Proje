package com.example.arife.gyk_proje;

/**
 * Created by Arife on 7.08.2017.
 */

public class User {
    private String name;
    private String mail;
    private String image;

    public User() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }
}
