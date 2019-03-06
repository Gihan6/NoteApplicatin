
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayTaskRequest {
    public DisplayTaskRequest(Long companyID) {
        this.companyID = companyID;
    }

    @SerializedName("CompanyID")
    private Long companyID;

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

}
