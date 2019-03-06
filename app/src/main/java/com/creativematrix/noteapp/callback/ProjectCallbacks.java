package com.creativematrix.noteapp.callback;

import com.creativematrix.noteapp.data.project.Project;

public interface ProjectCallbacks {
    void onAddProjectClicked();
    void onItemListClicked(Project project);

    void onStartDateClicked(int flag);
    void onEndDateClicked(int flag);
    void onStartTimeClicked(int flag);
    void onEndTimeClicked(int flag);
}
