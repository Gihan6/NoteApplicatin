
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Group {
    public Group(Long groupId,String companyId, String lang) {
        this.companyId = companyId;
        this.groupId = groupId;
        this.lang=lang;
    }

    public Group(String $id, String addBy, String companyId, String flag, Object groupDescreption, Object groupDescreptionen, Long groupId, Object groupImage, String groupName, Object groupNameen, Object imagebinary, Object lang, String message, Object msg) {
        this.$id = $id;
        this.addBy = addBy;
        this.companyId = companyId;
        this.flag = flag;
        this.groupDescreption = groupDescreption;
        this.groupDescreptionen = groupDescreptionen;
        this.groupId = groupId;
        this.groupImage = groupImage;
        this.groupName = groupName;
        this.groupNameen = groupNameen;
        this.imagebinary = imagebinary;
        this.lang = lang;
        this.message = message;
        this.msg = msg;
    }

    public Group() {

    }

    @Expose
    private String $id;
    @SerializedName("AddBy")
    private String addBy;
    @Expose
    private String companyId;
    @Expose
    private String flag;
    @Expose
    private Object groupDescreption;
    @Expose
    private Object groupDescreptionen;
    @SerializedName("GroupId")
    private Long groupId;
    @Expose
    private Object groupImage;
    @Expose
    private String groupName;
    @Expose
    private Object groupNameen;
    @Expose
    private Object imagebinary;
    @Expose
    private Object lang;
    @SerializedName("Message")
    private String message;
    @SerializedName("Msg")
    private Object msg;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getGroupDescreption() {
        return groupDescreption;
    }

    public void setGroupDescreption(Object groupDescreption) {
        this.groupDescreption = groupDescreption;
    }

    public Object getGroupDescreptionen() {
        return groupDescreptionen;
    }

    public void setGroupDescreptionen(Object groupDescreptionen) {
        this.groupDescreptionen = groupDescreptionen;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Object getGroupNameen() {
        return groupNameen;
    }

    public void setGroupNameen(Object groupNameen) {
        this.groupNameen = groupNameen;
    }

    public Object getImagebinary() {
        return imagebinary;
    }

    public void setImagebinary(Object imagebinary) {
        this.imagebinary = imagebinary;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}
