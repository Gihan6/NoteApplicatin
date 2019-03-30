
package com.creativematrix.noteapp.data.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayAllUsersRequest {

    public DisplayAllUsersRequest( Long companyId) {

        this.CompanyID = companyId;

    }


    @Expose
    private Long CompanyID;



}
