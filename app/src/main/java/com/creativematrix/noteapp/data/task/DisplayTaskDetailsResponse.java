
package com.creativematrix.noteapp.data.task;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayTaskDetailsResponse {

    @Expose
    private String $id;
    @SerializedName("Currencyid")
    private Long currencyid;
    @SerializedName("Currencyname")
    private String currencyname;
    @Expose
    private String flag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Pending")
    private Boolean pending;
    @SerializedName("TaskCost")
    private Long taskCost;
    @SerializedName("TaskDescription")
    private String taskDescription;
    @SerializedName("TaskEndTime")
    private String taskEndTime;
    @SerializedName("TaskFinshing")
    private Object taskFinshing;
    @SerializedName("TaskID")
    private Long taskID;
    @SerializedName("TaskName")
    private String taskName;
    @SerializedName("TaskStartTime")
    private String taskStartTime;
    @SerializedName("TaskState")
    private Long taskState;
    @SerializedName("TaskStautes")
    private Boolean taskStautes;
    @SerializedName("TaskUsers")
    private List<TaskUser> taskUsers;
    @SerializedName("Taskesfiles")
    private List<Taskesfile> taskesfiles;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(Long currencyid) {
        this.currencyid = currencyid;
    }

    public String getCurrencyname() {
        return currencyname;
    }

    public void setCurrencyname(String currencyname) {
        this.currencyname = currencyname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Long getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(Long taskCost) {
        this.taskCost = taskCost;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Object getTaskFinshing() {
        return taskFinshing;
    }

    public void setTaskFinshing(Object taskFinshing) {
        this.taskFinshing = taskFinshing;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Long getTaskState() {
        return taskState;
    }

    public void setTaskState(Long taskState) {
        this.taskState = taskState;
    }

    public Boolean getTaskStautes() {
        return taskStautes;
    }

    public void setTaskStautes(Boolean taskStautes) {
        this.taskStautes = taskStautes;
    }

    public List<TaskUser> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUser> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public List<Taskesfile> getTaskesfiles() {
        return taskesfiles;
    }

    public void setTaskesfiles(List<Taskesfile> taskesfiles) {
        this.taskesfiles = taskesfiles;
    }

}
