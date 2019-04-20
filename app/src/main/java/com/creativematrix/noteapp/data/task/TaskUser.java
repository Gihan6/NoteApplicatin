
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TaskUser {

    @Expose
    private String $id;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("USerTaskID")
    private Long uSerTaskID;
    @SerializedName("UserName")
    private String userName;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUSerTaskID() {
        return uSerTaskID;
    }

    public void setUSerTaskID(Long uSerTaskID) {
        this.uSerTaskID = uSerTaskID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
