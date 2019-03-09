
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Dirctote {

    @Expose
    private String $id;
    @SerializedName("DirctorName")
    private String dirctorName;
    @SerializedName("USerDirctotID")
    private Long uSerDirctotID;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getDirctorName() {
        return dirctorName;
    }

    public void setDirctorName(String dirctorName) {
        this.dirctorName = dirctorName;
    }

    public Long getUSerDirctotID() {
        return uSerDirctotID;
    }

    public void setUSerDirctotID(Long uSerDirctotID) {
        this.uSerDirctotID = uSerDirctotID;
    }

}
