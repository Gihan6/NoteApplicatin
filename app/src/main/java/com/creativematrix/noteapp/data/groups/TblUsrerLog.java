
package com.creativematrix.noteapp.data.groups;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class TblUsrerLog {

    @Expose
    private String $id;
    @Expose
    private Long addBy;
    @Expose
    private String addDate;
    @Expose
    private Long companyId;
    @Expose
    private Long groupId;
    @Expose
    private TblGroupe tblGroupe;
    @Expose
    private List<Object> tblUSertasks;
    @Expose
    private List<Object> tblUserProjects;
    @Expose
    private String userEmail;
    @Expose
    private Long userId;
    @Expose
    private String userName;
    @Expose
    private String userPassword;
    @Expose
    private String userPhone;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public TblGroupe getTblGroupe() {
        return tblGroupe;
    }

    public void setTblGroupe(TblGroupe tblGroupe) {
        this.tblGroupe = tblGroupe;
    }

    public List<Object> getTblUSertasks() {
        return tblUSertasks;
    }

    public void setTblUSertasks(List<Object> tblUSertasks) {
        this.tblUSertasks = tblUSertasks;
    }

    public List<Object> getTblUserProjects() {
        return tblUserProjects;
    }

    public void setTblUserProjects(List<Object> tblUserProjects) {
        this.tblUserProjects = tblUserProjects;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
