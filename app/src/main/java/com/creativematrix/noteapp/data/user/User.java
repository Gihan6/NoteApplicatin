package com.creativematrix.noteapp.data.user;

import java.io.Serializable;

public class User implements Serializable {
    public String UserEmail;
    public String UserPhone;
    public String userPassword;
    public long CompanyID;
    public String lang;
    public String Msg;

    public User(String userEmail, String userPhone, String userPassword, long companyID, String lang, String msg) {
        UserEmail = userEmail;
        UserPhone = userPhone;
        this.userPassword = userPassword;
        CompanyID = companyID;
        this.lang = lang;
        Msg = msg;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserEmail='" + UserEmail + '\'' +
                ", UserPhone='" + UserPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", CompanyID=" + CompanyID +
                ", lang='" + lang + '\'' +
                ", Msg='" + Msg + '\'' +
                '}';
    }
}
