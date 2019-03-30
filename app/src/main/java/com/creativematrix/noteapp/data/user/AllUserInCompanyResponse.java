
package com.creativematrix.noteapp.data.user;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AllUserInCompanyResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("lang")
    private Object mLang;
    @SerializedName("lst")
    private List<LstUsers> mLst;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("TaskName")
    private Object mTaskName;

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

    public Object getLang() {
        return mLang;
    }

    public void setLang(Object lang) {
        mLang = lang;
    }

    public List<LstUsers> getLst() {
        return mLst;
    }

    public void setLst(List<LstUsers> lst) {
        mLst = lst;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Object getTaskName() {
        return mTaskName;
    }

    public void setTaskName(Object taskName) {
        mTaskName = taskName;
    }

}
