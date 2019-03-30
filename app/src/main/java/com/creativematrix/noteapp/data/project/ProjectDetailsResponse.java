
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProjectDetailsResponse {

    @Expose
    private String $id;
    @Expose
    private Long coast;
    @Expose
    private String director;
    @Expose
    private String end;
    @Expose
    private String flag;
    @SerializedName("Message")
    private String message;
    @Expose
    private String name;
    @Expose
    private String owner;
    @Expose
    private String start;
    @Expose
    private Long state;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getCoast() {
        return coast;
    }

    public void setCoast(Long coast) {
        this.coast = coast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

}
