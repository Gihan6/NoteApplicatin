
package com.creativematrix.noteapp.data.project;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayProjectsResponse {

    @Expose
    private String $id;
    @SerializedName("Dirctotes")
    private List<Dirctote> dirctotes;
    @Expose
    private String flag;
    @Expose
    private Object lang;
    @SerializedName("Message")
    private String message;
    @Expose
    private List<Project> projects;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public List<Dirctote> getDirctotes() {
        return dirctotes;
    }

    public void setDirctotes(List<Dirctote> dirctotes) {
        this.dirctotes = dirctotes;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
