
package com.creativematrix.noteapp.data.task;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayTaskDetailsResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("Currencyid")
    private Long mCurrencyid;
    @SerializedName("Currencyname")
    private String mCurrencyname;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Pending")
    private Boolean mPending;
    @SerializedName("ProjectID")
    private Long mProjectID;
    @SerializedName("ProjectName")
    private String mProjectName;
    @SerializedName("TaskCost")
    private Long mTaskCost;
    @SerializedName("TaskDescription")
    private String mTaskDescription;
    @SerializedName("TaskEndTime")
    private String mTaskEndTime;
    @SerializedName("TaskFinshing")
    private Object mTaskFinshing;
    @SerializedName("TaskID")
    private Long mTaskID;
    @SerializedName("TaskName")
    private String mTaskName;
    @SerializedName("TaskStartTime")
    private String mTaskStartTime;
    @SerializedName("TaskState")
    private Long mTaskState;
    @SerializedName("TaskStautes")
    private Boolean mTaskStautes;
    @SerializedName("TaskUsers")
    private List<TaskUser> mTaskUsers;
    @SerializedName("Taskesfiles")
    private List<Taskesfile> mTaskesfiles;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public Long getCurrencyid() {
        return mCurrencyid;
    }

    public void setCurrencyid(Long currencyid) {
        mCurrencyid = currencyid;
    }

    public String getCurrencyname() {
        return mCurrencyname;
    }

    public void setCurrencyname(String currencyname) {
        mCurrencyname = currencyname;
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

    public Boolean getPending() {
        return mPending;
    }

    public void setPending(Boolean pending) {
        mPending = pending;
    }

    public Long getProjectID() {
        return mProjectID;
    }

    public void setProjectID(Long projectID) {
        mProjectID = projectID;
    }

    public String getProjectName() {
        return mProjectName;
    }

    public void setProjectName(String projectName) {
        mProjectName = projectName;
    }

    public Long getTaskCost() {
        return mTaskCost;
    }

    public void setTaskCost(Long taskCost) {
        mTaskCost = taskCost;
    }

    public String getTaskDescription() {
        return mTaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        mTaskDescription = taskDescription;
    }

    public String getTaskEndTime() {
        return mTaskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        mTaskEndTime = taskEndTime;
    }

    public Object getTaskFinshing() {
        return mTaskFinshing;
    }

    public void setTaskFinshing(Object taskFinshing) {
        mTaskFinshing = taskFinshing;
    }

    public Long getTaskID() {
        return mTaskID;
    }

    public void setTaskID(Long taskID) {
        mTaskID = taskID;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public String getTaskStartTime() {
        return mTaskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        mTaskStartTime = taskStartTime;
    }

    public Long getTaskState() {
        return mTaskState;
    }

    public void setTaskState(Long taskState) {
        mTaskState = taskState;
    }

    public Boolean getTaskStautes() {
        return mTaskStautes;
    }

    public void setTaskStautes(Boolean taskStautes) {
        mTaskStautes = taskStautes;
    }

    public List<TaskUser> getTaskUsers() {
        return mTaskUsers;
    }

    public void setTaskUsers(List<TaskUser> taskUsers) {
        mTaskUsers = taskUsers;
    }

    public List<Taskesfile> getTaskesfiles() {
        return mTaskesfiles;
    }

    public void setTaskesfiles(List<Taskesfile> taskesfiles) {
        mTaskesfiles = taskesfiles;
    }

}
