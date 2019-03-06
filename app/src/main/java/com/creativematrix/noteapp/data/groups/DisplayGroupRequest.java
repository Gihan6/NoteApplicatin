
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayGroupRequest {

    public DisplayGroupRequest(String addBy, String companyId, String groupId) {
        this.addBy = addBy;
        this.companyId = companyId;
        this.groupId = groupId;
    }

    @SerializedName("AddBy")
    private String addBy;
    @Expose
    private String companyId;
    @SerializedName("GroupId")
    private String groupId;

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
