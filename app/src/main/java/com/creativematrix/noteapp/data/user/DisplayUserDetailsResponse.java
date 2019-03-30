
package com.creativematrix.noteapp.data.user;

import java.util.List;

import com.creativematrix.noteapp.data.task.Task;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayUserDetailsResponse {

    @Expose
    private String $id;
    @Expose
    private String email;
    @Expose
    private String flag;
    @SerializedName("Message")
    private String message;
    @Expose
    private String name;
    @Expose
    private List<Task> tasks;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
