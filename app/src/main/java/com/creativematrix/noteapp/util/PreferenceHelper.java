/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

/**
 *
 */
package com.creativematrix.noteapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.creativematrix.noteapp.data.company.Company;


/**
 * @author Hitesh
 */
public class PreferenceHelper {

    public final static String FIRST_TIME = "FirstTime";
    public final static String WHATS_NEW_LAST_SHOWN = "whats_new_last_shown";
    public final static String SUBMIT_LOGS = "CrashLogs";
    // Handle Local Caching of data for responsiveness
    public static final String MY_CART_LIST_LOCAL = "MyCartItems";
    private static final String USER_LOGGED_IN = "isLoggedIn";
    private static final String ISCOMPANY = "isCompany";
    public static final String USER_COMPLETE_PROFILE = "isCompleteProfile";

    public static final String USER_CODE_VERIFCATION = "userCodeVerification";


    public static final String COMPANYID = "companyID";
    public static final String COMPANYEMAIL = "companyEmail";
    public static final String PASSWORD = "password";
    public static final String LOGOPATH = "logoPath";
    public static final String LOGIN_TYPE = "loginType";

    public static final String CUSTOMER_BIRTH_DATE = "BirthDate";
    public static final String CUSTOMER_FIRE_BASE_TOKEN = "FireBaseToken";
    public static final String LANG ="lang";
    public static final String AR ="AR";
    public static final String EN ="EN";
    public static final String LOGIN_INTENT = "LoginIntent";


    public static final String GPS_Latitude = "GPS_Latitude";
    public static final String GPS_Longitude = "GPS_Longitude";
    public static final String SHIPPING_ADDRESS_SET = "SHIPPING_ADDRESS_SET";
    public static final String LOCATION_ADDRESS = "LOCATION_ADDRESS";
    public static final String LOCATION_BUILDING_NUMBER = "LOCATION_BUILDING_NUMBER";
    public static final String LOCATION_FLOOR_NUMBER = "LOCATION_FLOOR_NUMBER";
    public static final String LOCATION_APPARTMENT_NUMBER = "LOCATION_APPARTMENT_NUMBER";


    public static final String GPS_Latitude_CURRENT_ADDRESS = "GPS_Latitude_CURRENT_ADDRESS";
    public static final String GPS_Longitude_CURRENT_ADDRESS = "GPS_Longitude_CURRENT_ADDRESS";

    //private static final String USER_TOKEN = "userCodeVerification";
    public static PreferenceHelper preferenceHelperInstance = new PreferenceHelper();

    private PreferenceHelper() {

    }

    public static PreferenceHelper getPrefernceHelperInstace() {
        return preferenceHelperInstance;
    }

    public void setBoolean(Context appContext, String key, Boolean value) {
        PreferenceManager.getDefaultSharedPreferences(appContext).edit()
                .putBoolean(key, value).apply();
    }

    public  void setAppLang(Context context, String value) {
        setString(context, LANG,value);
    }

    public String getAppLang(Context context) {
        return getString(context,LANG,EN);
    }

    public String getCompanyid(Context context) {
        return getString(context,COMPANYID,"");
    }


    public void setShippingAddressToNull(Context c) {
        setShippingAddress(false, c);
    }


    public void setCustomerData(Context c, String date, String phone, String token, String gender, String name) {

    }



    public void setInteger(Context appContext, String key, int value) {

        PreferenceManager.getDefaultSharedPreferences(appContext).edit()
                .putInt(key, value).apply();
    }

    public void setFloat(Context appContext, String key, float value) {

        PreferenceManager.getDefaultSharedPreferences(appContext).edit()
                .putFloat(key, value).apply();
    }

    public void setString(Context appContext, String key, String value) {

        PreferenceManager.getDefaultSharedPreferences(appContext).edit()
                .putString(key, value).apply();
    }


    // To retrieve values from shared preferences:

    public boolean getBoolean(Context appContext, String key,
                              Boolean defaultValue) {

        return PreferenceManager.getDefaultSharedPreferences(appContext)
                .getBoolean(key, defaultValue);
    }

    public int getInteger(Context appContext, String key, int defaultValue) {

        return PreferenceManager.getDefaultSharedPreferences(appContext)
                .getInt(key, defaultValue);
    }

    public float getString(Context appContext, String key, float defaultValue) {

        return PreferenceManager.getDefaultSharedPreferences(appContext)
                .getFloat(key, defaultValue);
    }

    public String getString(Context appContext, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
                .getString(key, defaultValue);
    }

    public void setIscompany(boolean UserLoggedIn, Context appContext) {
        setBoolean(appContext, ISCOMPANY, UserLoggedIn);
    }


    public boolean getIsCompany(Context appContext) {
        return getBoolean(appContext, ISCOMPANY, false);
    }
    public void setUserLoggedIn(boolean UserLoggedIn, Context appContext) {
        setBoolean(appContext, USER_LOGGED_IN, UserLoggedIn);
    }


    public boolean isUserLoggedIn(Context appContext) {
        return getBoolean(appContext, USER_LOGGED_IN, false);
    }

    public void setUSER_Complete_PROFILE(boolean USER_Complete_PROFILE, Context appContext) {
        setBoolean(appContext, USER_COMPLETE_PROFILE, USER_Complete_PROFILE);
    }
    public boolean isUSER_Complete_PROFILE(Context appContext) {
        return getBoolean(appContext, USER_COMPLETE_PROFILE, false);
    }
    public void setShippingAddress(boolean UserLoggedIn, Context appContext) {
        setBoolean(appContext, SHIPPING_ADDRESS_SET, UserLoggedIn);
    }


    public  void storeCompanyData(Context c, String companyID, String userName, String password,String logoPath,String Type) {

        setString(c, COMPANYID, companyID);
        setString(c, COMPANYEMAIL, userName);
        setString(c,PASSWORD , password);
        setString(c, LOGOPATH, logoPath);
        setString(c, LOGIN_TYPE, Type);
    }


}
