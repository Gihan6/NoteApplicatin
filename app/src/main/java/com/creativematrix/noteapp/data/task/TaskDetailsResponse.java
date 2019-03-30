
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TaskDetailsResponse {

    @Expose
    private String $id;
    @SerializedName("Descreption")
    private String descreption;
    @Expose
    private String enddate;
    @Expose
    private String flag;
    @Expose
    private String lstuser;
    @SerializedName("Message")
    private String message;
    @Expose
    private Long price;
    @SerializedName("Project")
    private String project;
    @Expose
    private String startdate;
    @Expose
    private String status;
    @SerializedName("Taskname")
    private String taskname;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLstuser() {
        return lstuser;
    }

    public void setLstuser(String lstuser) {
        this.lstuser = lstuser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

}
