
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Taskesfile {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("FileExt")
    private String mFileExt;
    @SerializedName("FileName")
    private String mFileName;
    @SerializedName("FileType")
    private String mFileType;
    @SerializedName("TakeID")
    private Long mTakeID;
    @SerializedName("TaskFileId")
    private Long mTaskFileId;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public String getFileExt() {
        return mFileExt;
    }

    public void setFileExt(String fileExt) {
        mFileExt = fileExt;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getFileType() {
        return mFileType;
    }

    public void setFileType(String fileType) {
        mFileType = fileType;
    }

    public Long getTakeID() {
        return mTakeID;
    }

    public void setTakeID(Long takeID) {
        mTakeID = takeID;
    }

    public Long getTaskFileId() {
        return mTaskFileId;
    }

    public void setTaskFileId(Long taskFileId) {
        mTaskFileId = taskFileId;
    }

}
