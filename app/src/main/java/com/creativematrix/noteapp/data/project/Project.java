
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Project implements Serializable {

    @Expose
    private String $id;
    @SerializedName("AddedID")
    private Long addedID;
     @SerializedName("CompanyID")
    private Long companyID;
    @SerializedName("DirctoresIDS")
    private Object dirctoresIDS;
    @SerializedName("DirectorIDs")
    private Object directorIDs;
    @SerializedName("EndTime")
    private String endTime;
    @Expose
    private Object flag;
    @Expose
    private Long id;
    @Expose
    private Object lang;
    @SerializedName("Msg")
    private Object msg;
    @SerializedName("ProJects")
    private Boolean proJects;
    @SerializedName("ProjectCost")
    private Long projectCost;
    @SerializedName("ProjectDescripation")
    private String projectDescripation;
    @SerializedName("ProjectName")
    private String projectName;
    @SerializedName("ProjectOwner")
    private String projectOwner;
    @SerializedName("ProjectStatus")
    private Long projectStatus;
    @SerializedName("StartTime")
    private String startTime;

    public Project(Long id) {
        this.id=id;
    }

    public String getDirctoresNames() {
        return DirctoresNames;
    }

    public void setDirctoresNames(String dirctoresNames) {
        DirctoresNames = dirctoresNames;
    }

    private String DirctoresNames;
    public Project(String projectName,
                   String startTime,
                   String endTime,
                   String projectOwner,
                   String projectDescripation,
                   String projectCost,
                   long addedID,
                   String msg,
                   String lang,
                   int projectStatus,
                   String directorIDs,
                   long companyID
                   ) {
        this.projectName = projectName;
        this.  startTime = startTime;
        this. endTime = endTime;
        this.  projectOwner = projectOwner;
        this. projectDescripation = projectDescripation;
        this. projectCost = Long.valueOf(projectCost);
        this. addedID = addedID;
        this. msg = msg;
        this.lang = lang;
        this. projectStatus = Long.valueOf(projectStatus);
        this. directorIDs = directorIDs;
this.companyID=companyID;

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

    public Object getDirctoresIDS() {
        return dirctoresIDS;
    }

    public void setDirctoresIDS(Object dirctoresIDS) {
        this.dirctoresIDS = dirctoresIDS;
    }

    public Object getDirectorIDs() {
        return directorIDs;
    }

    public void setDirectorIDs(Object directorIDs) {
        this.directorIDs = directorIDs;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getProJects() {
        return proJects;
    }

    public void setProJects(Boolean proJects) {
        this.proJects = proJects;
    }

    public Long getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Long projectCost) {
        this.projectCost = projectCost;
    }

    public String getProjectDescripation() {
        return projectDescripation;
    }

    public void setProjectDescripation(String projectDescripation) {
        this.projectDescripation = projectDescripation;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Long getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Long projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


}
