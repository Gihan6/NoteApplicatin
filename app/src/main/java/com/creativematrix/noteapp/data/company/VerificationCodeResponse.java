package com.creativematrix.noteapp.data.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationCodeResponse {


    @SerializedName("$id")
    @Expose
    private String id;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("ProccessDone")
    @Expose
    private Boolean proccessDone;
    @SerializedName("Message")
    @Expose
    private String message;

    public VerificationCodeResponse(String id, String lang, String phoneNumber, String code, Boolean proccessDone, String message) {
        this.id = id;
        this.lang = lang;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.proccessDone = proccessDone;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public Boolean getProccessDone() {
        return proccessDone;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "VerificationCodeResponse{" +
                "id='" + id + '\'' +
                ", lang='" + lang + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", code='" + code + '\'' +
                ", proccessDone=" + proccessDone +
                ", message='" + message + '\'' +
                '}';
    }
}
