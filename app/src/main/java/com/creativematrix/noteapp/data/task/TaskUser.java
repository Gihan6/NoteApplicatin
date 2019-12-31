
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TaskUser implements Serializable {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("Phone")
    private String mPhone;
    @SerializedName("USerTaskID")
    private Long mUSerTaskID;
    @SerializedName("UserName")
    private String mUserName;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Long getUSerTaskID() {
        return mUSerTaskID;
    }

    public void setUSerTaskID(Long uSerTaskID) {
        mUSerTaskID = uSerTaskID;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
