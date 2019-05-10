package com.creativematrix.noteapp.data.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("$id")
    @Expose
    private String id;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Username")
    @Expose
    private String username;

    public String getPhotoLink() {
        return PhotoLink;
    }

    public void setPhotoLink(String photoLink) {
        PhotoLink = photoLink;
    }

    @SerializedName("PhotoLink")
    @Expose
    private String PhotoLink;

    @SerializedName("IsAdmin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("MessageText")
    @Expose
    private String messageText;
    @SerializedName("flag")
    @Expose
    private String flag;

    public String getFlag() {
        return flag;
    }

    public String getId() {
        return id;
    }

    public Integer getiD() {
        return iD;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id='" + id + '\'' +
                ", iD=" + iD +
                ", companyName='" + companyName + '\'' +
                ", username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                ", messageText='" + messageText + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}

