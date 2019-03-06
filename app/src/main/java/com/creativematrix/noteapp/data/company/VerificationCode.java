package com.creativematrix.noteapp.data.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationCode {
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;

    public VerificationCode(String lang, String phoneNumber) {
        this.lang = lang;
        this.phoneNumber = phoneNumber;
    }

    public String getLang() {
        return lang;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "VerificationCode{" +
                "lang='" + lang + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
