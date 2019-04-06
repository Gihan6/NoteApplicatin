package com.creativematrix.noteapp.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    public String UserEmail;
    public String UserPhone;
    public String userPassword;
    public long CompanyID;
    public String lang;
    public String Msg;
    @SerializedName("UserId")
    private Long mUserId;
    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(long companyID) {
        CompanyID = companyID;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Long getGroupID() {
        return GroupID;
    }

    public void setGroupID(Long groupID) {
        GroupID = groupID;
    }

    public Long GroupID;

    public User(String userEmail, String userPhone, String userPassword, long companyID,long mGroupID, String lang, String msg) {
        UserEmail = userEmail;
        UserPhone = userPhone;
        this.userPassword = userPassword;
        CompanyID = companyID;
        this.lang = lang;
        Msg = msg;
        this.GroupID=mGroupID;
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

    public Long getmUserId() {
        return mUserId;
    }

    public void setmUserId(Long mUserId) {
        this.mUserId = mUserId;
    }
}
