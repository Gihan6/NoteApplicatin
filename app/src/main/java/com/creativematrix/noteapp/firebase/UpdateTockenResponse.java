package com.creativematrix.noteapp.firebase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTockenResponse {

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("Message")
    @Expose
    private String message;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
