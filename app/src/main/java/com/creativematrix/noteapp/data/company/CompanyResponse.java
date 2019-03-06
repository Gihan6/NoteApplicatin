package com.creativematrix.noteapp.data.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyResponse {

    @SerializedName("$id")
    @Expose
    private String id;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("CompanyID")
    @Expose
    private long CompanyID;

    public String getId() {
        return id;
    }

    public String getFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }

    public long getCompanyID() {
        return CompanyID;
    }

    @Override
    public String toString() {
        return "CompanyResponse{" +
                "id='" + id + '\'' +
                ", flag='" + flag + '\'' +
                ", message='" + message + '\'' +
                ", CompanyID=" + CompanyID +
                '}';
    }
}
