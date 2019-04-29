
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DeleteTaskResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Tasks")
    private Object mTasks;

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

    public Object getTasks() {
        return mTasks;
    }

    public void setTasks(Object tasks) {
        mTasks = tasks;
    }

}
