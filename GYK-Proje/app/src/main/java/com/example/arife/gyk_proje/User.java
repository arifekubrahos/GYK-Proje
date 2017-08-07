package com.example.arife.gyk_proje;

/**
 * Created by Arife on 7.08.2017.
 */

public class User {
    private String name;
    private String mail;
    //privvate String image; resim ekledeğimizde koyacağız

    public User() {
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
