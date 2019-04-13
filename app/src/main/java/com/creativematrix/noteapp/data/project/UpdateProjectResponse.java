
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UpdateProjectResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("Message")
    private String mMessage;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public String getFlag() {
        return mFlag;
    }

    public void setFlag(String flag) {
        mFlag = flag;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
