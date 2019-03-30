
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Userslst {

    @Expose
    private String $id;
    @SerializedName("CompanyID")
    private Long companyID;
    @Expose
    private Object flag;
    @SerializedName("GroupName")
    private Object groupName;
    @Expose
    private Object lang;
    @SerializedName("Msg")
    private Object msg;
    @Expose
    private Long tasknumber;
    @SerializedName("UserEmail")
    private String userEmail;
    @SerializedName("UserId")
    private Long userId;
    @SerializedName("UserName")
    private String userName;
    @Expose
    private String userPassword;
    @SerializedName("UserPhone")
    private String userPhone;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public Object getGroupName() {
        return groupName;
    }

    public void setGroupName(Object groupName) {
        this.groupName = groupName;
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

    public Long getTasknumber() {
        return tasknumber;
    }

    public void setTasknumber(Long tasknumber) {
        this.tasknumber = tasknumber;
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
