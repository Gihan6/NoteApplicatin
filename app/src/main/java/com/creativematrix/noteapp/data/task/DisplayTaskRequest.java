
package com.creativematrix.noteapp.data.task;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayTaskRequest {
    public DisplayTaskRequest(Long companyID,String Lang) {
        this.companyID = companyID;
        this.lang=Lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @SerializedName("CompanyID")
    private Long companyID;
    String lang;
    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

}
