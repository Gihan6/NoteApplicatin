
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayGroupRequest {

    public DisplayGroupRequest(Long addBy, Long companyId, Long groupId) {
        this.AddBy = addBy;
        this.CompanyID = companyId;
        this.GroupId = groupId;
    }

    @SerializedName("AddBy")
    private Long AddBy;
    @Expose
    private Long CompanyID;
    @SerializedName("GroupId")
    private Long GroupId;



}
