package com.creativematrix.noteapp.firebase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTockenRequest {

    @SerializedName("TypeNo")
    @Expose
    private Integer typeNo;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("ID")
    @Expose
    private Long iD;

    public Integer getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(Integer typeNo) {
        this.typeNo = typeNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getID() {
        return iD;
    }

    public void setID(Long iD) {
        this.iD = iD;
    }
}
