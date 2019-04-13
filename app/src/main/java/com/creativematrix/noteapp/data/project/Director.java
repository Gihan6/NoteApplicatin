
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Director {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("DirectorID")
    private Long mDirectorID;
    @SerializedName("Name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public Long getDirectorID() {
        return mDirectorID;
    }

    public void setDirectorID(Long directorID) {
        mDirectorID = directorID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

}
