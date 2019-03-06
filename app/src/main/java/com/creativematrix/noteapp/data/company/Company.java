package com.creativematrix.noteapp.data.company;

import java.io.Serializable;

public class Company implements Serializable{
    public String lang;
    public String CompanyArName;
    public String CompanyEnName;
    public String ComapnyArDetails;
    public String ComapnyEnDetails;
    public String CompanyPhone;
    public String Username;
    public String Password;
    public String LogoPath;

    public Company(String lang, String companyArName, String companyEnName, String comapnyArDetails, String comapnyEnDetails, String companyPhone, String username, String password, String logoPath) {
        this.lang = lang;
        CompanyArName = companyArName;
        CompanyEnName = companyEnName;
        ComapnyArDetails = comapnyArDetails;
        ComapnyEnDetails = comapnyEnDetails;
        CompanyPhone = companyPhone;
        Username = username;
        Password = password;
        LogoPath = logoPath;
    }

    @Override
    public String toString() {
        return "Company{" +
                "lang='" + lang + '\'' +
                ", CompanyArName='" + CompanyArName + '\'' +
                ", CompanyEnName='" + CompanyEnName + '\'' +
                ", ComapnyArDetails='" + ComapnyArDetails + '\'' +
                ", ComapnyEnDetails='" + ComapnyEnDetails + '\'' +
                ", CompanyPhone='" + CompanyPhone + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", LogoPath='" + LogoPath + '\'' +
                '}';
    }
}
