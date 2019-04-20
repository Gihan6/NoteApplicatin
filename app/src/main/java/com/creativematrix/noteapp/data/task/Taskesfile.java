
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Taskesfile {

    @Expose
    private String $id;
    @SerializedName("FileExt")
    private String fileExt;
    @SerializedName("FileName")
    private String fileName;
    @SerializedName("FileType")
    private String fileType;
    @SerializedName("TakeID")
    private Long takeID;
    @SerializedName("TaskFileId")
    private Long taskFileId;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getTakeID() {
        return takeID;
    }

    public void setTakeID(Long takeID) {
        this.takeID = takeID;
    }

    public Long getTaskFileId() {
        return taskFileId;
    }

    public void setTaskFileId(Long taskFileId) {
        this.taskFileId = taskFileId;
    }

}
