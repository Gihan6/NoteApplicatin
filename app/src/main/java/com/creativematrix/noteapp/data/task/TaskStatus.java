package com.creativematrix.noteapp.data.task;

public class TaskStatus {
    String taskStatusName;
    String ID;

    public TaskStatus(String taskStatusName, String ID) {
        this.taskStatusName = taskStatusName;
        this.ID = ID;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
