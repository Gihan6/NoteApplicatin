
package com.creativematrix.noteapp.data.groups;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayGroupResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("lstGroup")
    private List<LstGroup> mLstGroup;
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

    public List<LstGroup> getLstGroup() {
        return mLstGroup;
    }

    public void setLstGroup(List<LstGroup> lstGroup) {
        mLstGroup = lstGroup;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
