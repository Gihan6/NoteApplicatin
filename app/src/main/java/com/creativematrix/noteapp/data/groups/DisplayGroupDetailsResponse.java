
package com.creativematrix.noteapp.data.groups;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DisplayGroupDetailsResponse {

    @Expose
    private String $id;
    @Expose
    private String flag;
    @SerializedName("Grouplogo")
    private String grouplogo;
    @SerializedName("Message")
    private String message;
    @Expose
    private List<Userslst> userslst;
    @Expose
    private Long usertasks;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGrouplogo() {
        return grouplogo;
    }

    public void setGrouplogo(String grouplogo) {
        this.grouplogo = grouplogo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Userslst> getUserslst() {
        return userslst;
    }

    public void setUserslst(List<Userslst> userslst) {
        this.userslst = userslst;
    }

    public Long getUsertasks() {
        return usertasks;
    }

    public void setUsertasks(Long usertasks) {
        this.usertasks = usertasks;
    }

}
