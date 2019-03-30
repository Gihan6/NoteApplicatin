
package com.creativematrix.noteapp.data.user;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AddUserResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("CompanyID")
    private Long mCompanyID;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("GroupName")
    private Object mGroupName;
    @SerializedName("lang")
    private Object mLang;
    @SerializedName("Msg")
    private String mMsg;
    @SerializedName("tasknumber")
    private Long mTasknumber;
    @SerializedName("UserEmail")
    private Object mUserEmail;
    @SerializedName("UserId")
    private Long mUserId;
    @SerializedName("UserName")
    private Object mUserName;
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

    public String getFlag() {
        return mFlag;
    }

    public void setFlag(String flag) {
        mFlag = flag;
    }

    public Object getGroupName() {
        return mGroupName;
    }

    public void setGroupName(Object groupName) {
        mGroupName = groupName;
    }

    public Object getLang() {
        return mLang;
    }

    public void setLang(Object lang) {
        mLang = lang;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
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

    public Object getUserName() {
        return mUserName;
    }

    public void setUserName(Object userName) {
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
