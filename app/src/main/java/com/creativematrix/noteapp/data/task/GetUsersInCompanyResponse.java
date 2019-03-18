
package com.creativematrix.noteapp.data.task;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GetUsersInCompanyResponse {

    @Expose
    private String $id;
    @SerializedName("CompanyID")
    private Long companyID;
    @SerializedName("CountUsers")
    private Long countUsers;
    @Expose
    private String flag;
    @Expose
    private String lang;
    @Expose

    @SerializedName("lstUsersnCompany")
    private List<LstUsersnCompnay> lstUsersnCompnay;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public Long getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(Long countUsers) {
        this.countUsers = countUsers;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<LstUsersnCompnay> getLstUsersnCompnay() {
        return lstUsersnCompnay;
    }

    public void setLstUsersnCompnay(List<LstUsersnCompnay> lstUsersnCompnay) {
        this.lstUsersnCompnay = lstUsersnCompnay;
    }

}
