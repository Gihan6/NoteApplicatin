
package com.creativematrix.noteapp.data.user;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LstUsers {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("CompanyID")
    private Long mCompanyID;
    @SerializedName("flag")
    private Object mFlag;
    @SerializedName("GroupName")
    private String mGroupName;
    @SerializedName("lang")
    private Object mLang;
    @SerializedName("Msg")
    private Object mMsg;
    @SerializedName("tasknumber")
    private Long mTasknumber;
    @SerializedName("UserEmail")
    private Object mUserEmail;
    @SerializedName("UserId")
    private Long mUserId;
    @SerializedName("UserName")
    private String mUserName;
    @SerializedName("userPassword")
    private Object mUserPassword;
    @SerializedName("UserPhone")
    private Object mUserPhone;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public Long getCompanyID() {
        return mCompanyID;
    }

    public void setCompanyID(Long companyID) {
        mCompanyID = companyID;
    }

    public Object getFlag() {
        return mFlag;
    }

    public void setFlag(Object flag) {
        mFlag = flag;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public Object getLang() {
        return mLang;
    }

    public void setLang(Object lang) {
        mLang = lang;
    }

    public Object getMsg() {
        return mMsg;
    }

    public void setMsg(Object msg) {
        mMsg = msg;
    }

    public Long getTasknumber() {
        return mTasknumber;
    }

    public void setTasknumber(Long tasknumber) {
        mTasknumber = tasknumber;
    }

    public Object getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(Object userEmail) {
        mUserEmail = userEmail;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public Object getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(Object userPassword) {
        mUserPassword = userPassword;
    }

    public Object getUserPhone() {
        return mUserPhone;
    }

    public void setUserPhone(Object userPhone) {
        mUserPhone = userPhone;
    }

}
