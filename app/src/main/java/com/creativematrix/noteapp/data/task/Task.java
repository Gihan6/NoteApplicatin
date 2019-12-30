
package com.creativematrix.noteapp.data.task;

import com.creativematrix.noteapp.data.project.Dirctote;
import com.creativematrix.noteapp.util.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Task {
    @Expose
    private String $id;
    @SerializedName("AddedID")
    private Long addedID;
    @SerializedName("CompanyID")
    private Long companyID;
    @SerializedName("EndTime")
    private Object endTime;
    @SerializedName("Taskesfiles")
    private List<FilesBinary> Taskesfiles;
    @SerializedName("FilesBinary")
    private List<FilesBinary> FilesBinary;
    @Expose
    private Object flag;
    @SerializedName("IsAdmin")
    private Boolean isAdmin;
    @Expose
    private Object lang;
    @SerializedName("Msg")
    private Object msg;
    @SerializedName("ProjectID")
    private Long projectID;
    @SerializedName("StartTime")
    private Object startTime;
    @SerializedName("TaskCost")
    private Long taskCost;
    @SerializedName("TaskDescripation")
    private String taskDescripation;
    @SerializedName("CurrencyID")
    private String CurrencyID;
    @SerializedName("TaskDescripationEn")
    private String taskDescripationEn;
    @SerializedName("TaskID")
    private Long TaskID;
    @SerializedName("TaskName")
    private String taskName;
    @SerializedName("TaskStatus")
    private boolean taskStatus;
    @SerializedName("Pending")
    private Boolean pending;
    @SerializedName("TaskState")
    private Long TaskState;
    @SerializedName("UserID")
    private Long userID;
    @SerializedName("UsersIDs")
    private Object usersIDs;

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Long getTaskState() {
        return TaskState;
    }

    public void setTaskState(Long taskState) {
        TaskState = taskState;
    }
    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getAddedID() {
        return addedID;
    }

    public void setAddedID(Long addedID) {
        this.addedID = addedID;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }



    public String getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(String currencyID) {
        CurrencyID = currencyID;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {

        this.startTime = startTime;
    }

    public Long getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(Long taskCost) {
        this.taskCost = taskCost;
    }

    public String getTaskDescripation() {
        return taskDescripation;
    }

    public void setTaskDescripation(String taskDescripation) {
        this.taskDescripation = taskDescripation;
    }

    public String getTaskDescripationEn() {
        return taskDescripationEn;
    }

    public void setTaskDescripationEn(String taskDescripationEn) {
        this.taskDescripationEn = taskDescripationEn;
    }

    public Long getTaskID() {
        return TaskID;
    }

    public void setTaskID(Long taskID) {
        this.TaskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Object getUsersIDs() {
        return usersIDs;
    }

    public void setUsersIDs(Object usersIDs) {
        this.usersIDs = usersIDs;
    }
    public List<FilesBinary> getFilesBinaryList() {
        return FilesBinary;
    }

    public void setFilesBinaryList(List<FilesBinary> filesBinaryList) {
        this.FilesBinary = filesBinaryList;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<com.creativematrix.noteapp.data.task.FilesBinary> getTaskesfiles() {
        return Taskesfiles;
    }

    public void setTaskesfiles(List<com.creativematrix.noteapp.data.task.FilesBinary> taskesfiles) {
        Taskesfiles = taskesfiles;
    }
}
