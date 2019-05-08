
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class LstGroup implements Serializable {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("AddBy")
    private Long mAddBy;
    @SerializedName("companyId")
    private Long mCompanyId;
    @SerializedName("groupDescreption")
    private String mGroupDescreption;
    @SerializedName("groupDescreptionen")
    private Object mGroupDescreptionen;
    @SerializedName("GroupId")
    private Long mGroupId;
    @SerializedName("groupName")
    private String mGroupName;
    @SerializedName("groupNameen")
    private Object mGroupNameen;
    @SerializedName("imagebinary")
    private String mImagebinary;
    @SerializedName("Img")
    private String mImg;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public Long getAddBy() {
        return mAddBy;
    }

    public void setAddBy(Long addBy) {
        mAddBy = addBy;
    }

    public Long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(Long companyId) {
        mCompanyId = companyId;
    }

    public String getGroupDescreption() {
        return mGroupDescreption;
    }

    public void setGroupDescreption(String groupDescreption) {
        mGroupDescreption = groupDescreption;
    }

    public Object getGroupDescreptionen() {
        return mGroupDescreptionen;
    }

    public void setGroupDescreptionen(Object groupDescreptionen) {
        mGroupDescreptionen = groupDescreptionen;
    }

    public Long getGroupId() {
        return mGroupId;
    }

    public void setGroupId(Long groupId) {
        mGroupId = groupId;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public Object getGroupNameen() {
        return mGroupNameen;
    }

    public void setGroupNameen(Object groupNameen) {
        mGroupNameen = groupNameen;
    }

    public String getImagebinary() {
        return mImagebinary;
    }

    public void setImagebinary(String imagebinary) {
        mImagebinary = imagebinary;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

}
