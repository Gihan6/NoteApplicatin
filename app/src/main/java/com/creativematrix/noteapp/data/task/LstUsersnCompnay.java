
package com.creativematrix.noteapp.data.task;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LstUsersnCompnay implements Serializable {


    private String $id;

    private Long GroupID;

    private String GroupName;

    private Long UserID;

    private String Username;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getGroupID() {
        return GroupID;
    }

    public void setGroupID(Long groupID) {
        GroupID = groupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

}
