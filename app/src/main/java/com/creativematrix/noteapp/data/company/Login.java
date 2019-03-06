package com.creativematrix.noteapp.data.company;

import java.io.Serializable;

public class Login implements Serializable{
    public String lang;
    public int Type;
    public String Username;
    public String Password;

    public Login(String lang, int type, String username, String password) {
        this.lang = lang;
        Type = type;
        Username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "lang='" + lang + '\'' +
                ", Type='" + Type + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
