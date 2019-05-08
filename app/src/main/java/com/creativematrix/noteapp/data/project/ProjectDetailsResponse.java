
package com.creativematrix.noteapp.data.project;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProjectDetailsResponse {

    @SerializedName("$id")
    private String m$id;
    @SerializedName("coast")
    private Long mCoast;
    @SerializedName("Currencyid")
    private Long mCurrencyid;
    @SerializedName("Currencyname")
    private Object mCurrencyname;
    @SerializedName("director")
    private List<Director> mDirector;
    @SerializedName("end")
    private String mEnd;
    @SerializedName("flag")
    private String mFlag;
    @SerializedName("Id")
    private Long mId;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("name")
    private String mName;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("ProjectDescripations")
    private String mProjectDescripations;
    @SerializedName("start")
    private String mStart;
    @SerializedName("state")
    private Long mState;
    @SerializedName("States")
    private Boolean mStates;

    public String get$id() {
        return m$id;
    }

    public void set$id(String $id) {
        m$id = $id;
    }

    public Long getCoast() {
        return mCoast;
    }

    public void setCoast(Long coast) {
        mCoast = coast;
    }

    public Long getCurrencyid() {
        return mCurrencyid;
    }

    public void setCurrencyid(Long currencyid) {
        mCurrencyid = currencyid;
    }

    public Object getCurrencyname() {
        return mCurrencyname;
    }

    public void setCurrencyname(Object currencyname) {
        mCurrencyname = currencyname;
    }

    public List<Director> getDirector() {
        return mDirector;
    }

    public void setDirector(List<Director> director) {
        mDirector = director;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

    public String getFlag() {
        return mFlag;
    }

    public void setFlag(String flag) {
        mFlag = flag;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getProjectDescripations() {
        return mProjectDescripations;
    }

    public void setProjectDescripations(String projectDescripations) {
        mProjectDescripations = projectDescripations;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public Long getState() {
        return mState;
    }

    public void setState(Long state) {
        mState = state;
    }

    public Boolean getStates() {
        return mStates;
    }

    public void setStates(Boolean states) {
        mStates = states;
    }

}
