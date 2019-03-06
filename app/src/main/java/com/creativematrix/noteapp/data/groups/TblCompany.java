
package com.creativematrix.noteapp.data.groups;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TblCompany {

    @Expose
    private String $id;
    @Expose
    private Long addBy;
    @Expose
    private String addDate;
    @Expose
    private String adminPassword;
    @Expose
    private String adminUser;
    @Expose
    private String companyDescripation;
    @Expose
    private String companyDescripationInEnglish;
    @SerializedName("CompanyEmail")
    private Object companyEmail;
    @Expose
    private Long companyId;
    @Expose
    private String companyImage;
    @SerializedName("CompanyLogoBinary")
    private Object companyLogoBinary;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("CompanyNameInEnglish")
    private String companyNameInEnglish;
    @Expose
    private String companyPhone;
    @SerializedName("Companystatus")
    private Boolean companystatus;
    @Expose
    private List<TblGroupe> tblGroupes;
    @Expose
    private List<Object> tblProjects;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getAddBy() {
        return addBy;
    }

    public void setAddBy(Long addBy) {
        this.addBy = addBy;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getCompanyDescripation() {
        return companyDescripation;
    }

    public void setCompanyDescripation(String companyDescripation) {
        this.companyDescripation = companyDescripation;
    }

    public String getCompanyDescripationInEnglish() {
        return companyDescripationInEnglish;
    }

    public void setCompanyDescripationInEnglish(String companyDescripationInEnglish) {
        this.companyDescripationInEnglish = companyDescripationInEnglish;
    }

    public Object getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(Object companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public Object getCompanyLogoBinary() {
        return companyLogoBinary;
    }

    public void setCompanyLogoBinary(Object companyLogoBinary) {
        this.companyLogoBinary = companyLogoBinary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameInEnglish() {
        return companyNameInEnglish;
    }

    public void setCompanyNameInEnglish(String companyNameInEnglish) {
        this.companyNameInEnglish = companyNameInEnglish;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Boolean getCompanystatus() {
        return companystatus;
    }

    public void setCompanystatus(Boolean companystatus) {
        this.companystatus = companystatus;
    }

    public List<TblGroupe> getTblGroupes() {
        return tblGroupes;
    }

    public void setTblGroupes(List<TblGroupe> tblGroupes) {
        this.tblGroupes = tblGroupes;
    }

    public List<Object> getTblProjects() {
        return tblProjects;
    }

    public void setTblProjects(List<Object> tblProjects) {
        this.tblProjects = tblProjects;
    }

}
