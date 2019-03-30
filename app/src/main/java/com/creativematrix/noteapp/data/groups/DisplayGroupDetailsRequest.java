
package com.creativematrix.noteapp.data.groups;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayGroupDetailsRequest {
    public DisplayGroupDetailsRequest(Long mGroupId) {
        this.mGroupId = mGroupId;
    }

    @SerializedName("GroupId")
    private Long mGroupId;

    public Long getGroupId() {
        return mGroupId;
    }

    public void setGroupId(Long groupId) {
        mGroupId = groupId;
    }

}
