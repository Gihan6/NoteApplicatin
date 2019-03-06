package com.creativematrix.noteapp.data.company;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CompanyApiInterface {

    @POST("AddCompany/")
    Call<CompanyResponse> postAddCompany(@Body Company company);

    @POST("Login/")
    Call<LoginResponse> login(@Body Login login);

    @POST("SendValidationCode/")
    Call<VerificationCodeResponse> verify(@Body VerificationCode verificationCode);

    @POST("CheckCompanyPhoneMethod/")
    Call<CheckEmailAndPhone> checkPhone(@Body CheckEmailAndPhone checkEmailAndPhone);

    @POST("CheckCompanyEmailMethod/")
    Call<CheckEmailAndPhone> checkMail(@Body CheckEmailAndPhone checkEmailAndPhone);

    @POST("CheckCompanyNameMethod/")
    Call<CheckEmailAndPhone> checkName(@Body CheckEmailAndPhone checkEmailAndPhone);


}
