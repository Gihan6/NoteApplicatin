
package com.creativematrix.noteapp.data.project;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class DeleteProjectRequest {

    public DeleteProjectRequest(String id) {
        this.id = id;
    }

    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
