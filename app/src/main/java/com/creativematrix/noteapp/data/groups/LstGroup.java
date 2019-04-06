
package com.creativematrix.noteapp.data.groups;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LstGroup implements Serializable {

    @Expose
    private String $id;
    @Expose
    private Long addBy;
    @Expose
    private String addDate;
    @Expose
    private Long companyid;
    @Expose
    private String groupDescreption;
    @Expose
    private Object groupDescreptionen;
    @Expose
    @SerializedName("GroupId")
    private Long groupId;
    @Expose
    private Object groupImage;
    @Expose
    private String groupName;
    @Expose
    private Object groupNameInEnglish;
    @Expose
    private String groupbinaryimage;
    @Expose
    private TblCompany tblCompany;
    @Expose
    private List<TblUsrerLog> tblUsrerLogs;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getAddBy() {
        return addBy;
    }

    public void setAddBy(Long addBy) {
        this.addBy = addBy;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public Long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getGroupDescripation() {
        return groupDescreption;
    }

    public void setGroupDescripation(String groupDescripation) {
        this.groupDescreption = groupDescripation;
    }

    public Object getGroupDescripationInEnglish() {
        return groupDescreptionen;
    }

    public void setGroupDescripationInEnglish(Object groupDescripationInEnglish) {
        this.groupDescreptionen = groupDescripationInEnglish;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Object getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(Object groupImage) {
        this.groupImage = groupImage;
    }

    public String getMGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Object getGroupNameInEnglish() {
        return groupNameInEnglish;
    }

    public void setGroupNameInEnglish(Object groupNameInEnglish) {
        this.groupNameInEnglish = groupNameInEnglish;
    }

    public String getGroupbinaryimage() {
        return groupbinaryimage;
    }

    public void setGroupbinaryimage(String groupbinaryimage) {
        this.groupbinaryimage = groupbinaryimage;
    }

    public TblCompany getTblCompany() {
        return tblCompany;
    }

    public void setTblCompany(TblCompany tblCompany) {
        this.tblCompany = tblCompany;
    }

    public List<TblUsrerLog> getTblUsrerLogs() {
        return tblUsrerLogs;
    }

    public void setTblUsrerLogs(List<TblUsrerLog> tblUsrerLogs) {
        this.tblUsrerLogs = tblUsrerLogs;
    }

}
