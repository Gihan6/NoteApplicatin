
package com.creativematrix.noteapp.data.user;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class DisplayUserDetailsRequest {

    @SerializedName("UserId")
    private Long userId;

    public DisplayUserDetailsRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
