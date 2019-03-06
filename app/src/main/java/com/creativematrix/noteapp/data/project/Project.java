package com.creativematrix.noteapp.data.project;

public class Project {
    public String ProjectName;
    public String StartTime;
    public String EndTime;
    public String ProjectOwner;
    public String ProjectDescripation;
    public String ProjectCost;
    public long AddedID;
    public String Msg;
    public String lang;
    public int ProjectStatus;
    public String DirectorIDs;

    public Project(String projectName,
                   String startTime,
                   String endTime,
                   String projectOwner,
                   String projectDescripation,
                   String projectCost,
                   long addedID,
                   String msg,
                   String lang,
                   int projectStatus,
                   String directorIDs) {
        ProjectName = projectName;
        StartTime = startTime;
        EndTime = endTime;
        ProjectOwner = projectOwner;
        ProjectDescripation = projectDescripation;
        ProjectCost = projectCost;
        AddedID = addedID;
        Msg = msg;
        this.lang = lang;
        ProjectStatus = projectStatus;
        DirectorIDs = directorIDs;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ProjectName='" + ProjectName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", ProjectOwner='" + ProjectOwner + '\'' +
                ", ProjectDescripation='" + ProjectDescripation + '\'' +
                ", ProjectCost='" + ProjectCost + '\'' +
                ", AddedID=" + AddedID +
                ", Msg='" + Msg + '\'' +
                ", lang='" + lang + '\'' +
                ", ProjectStatus=" + ProjectStatus +
                ", DirectorIDs='" + DirectorIDs + '\'' +
                '}';
    }
}
