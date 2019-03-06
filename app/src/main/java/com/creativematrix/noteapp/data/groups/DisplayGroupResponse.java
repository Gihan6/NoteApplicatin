
package com.creativematrix.noteapp.data.groups;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayGroupResponse {

    @Expose
    private String $id;
    @Expose
    private String flag;
    @Expose
    private List<LstGroup> lstGroup;
    @SerializedName("Message")
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

    public List<LstGroup> getLstGroup() {
        return lstGroup;
    }

    public void setLstGroup(List<LstGroup> lstGroup) {
        this.lstGroup = lstGroup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
