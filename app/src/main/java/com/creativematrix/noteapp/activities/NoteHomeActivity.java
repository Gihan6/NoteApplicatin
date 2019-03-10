package com.creativematrix.noteapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.DateTimeCallbacks;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.callback.TaskCallbacks;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.fragments.AddNewGroupFragment;
import com.creativematrix.noteapp.fragments.AddNewProjectFragment;
import com.creativematrix.noteapp.fragments.AddNewTaskFragment;
import com.creativematrix.noteapp.fragments.ViewAllGroupsFragment;
import com.creativematrix.noteapp.fragments.ViewAllProjectsFragment;
import com.creativematrix.noteapp.fragments.ViewAllTasksFragment;
import com.creativematrix.noteapp.util.Utils;

public class NoteHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProjectCallbacks, DateTimeCallbacks {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FragmentManager mFragmentManager;
    // private ViewAllProjectsFragment viewAllProjectsFragment;
    private AddNewProjectFragment addNewProjectFragment;
    private AddNewTaskFragment addNewTaskFragment;

    private int timeDateFlag;

    public DrawerLayout getmDrawerLayout() {
        return drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        //   viewAllProjectsFragment = new ViewAllProjectsFragment();
        Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new ViewAllTasksFragment(), NoteHomeActivity.this, Utils.VIEWAllTASKSFRAGGMENT, Utils.AnimationType.SLIDE_UP);


        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.note_home_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_projects) {
            Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new ViewAllProjectsFragment(), NoteHomeActivity.this, Utils.VIEWAllPROJECTSFRAGGMENT, Utils.AnimationType.SLIDE_UP);

        } else if (id == R.id.nav_tasks) {
            Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new ViewAllTasksFragment(), NoteHomeActivity.this, Utils.VIEWAllTASKSFRAGGMENT, Utils.AnimationType.SLIDE_UP);

        } else if (id == R.id.nav_groups) {
            Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new ViewAllGroupsFragment(), NoteHomeActivity.this, Utils.VIEWAllGROUPSFRAGGMENT, Utils.AnimationType.SLIDE_UP);

        } else if (id == R.id.nav_members) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAddProjectClicked() {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder_home, addNewProjectFragment)
                .commit();
    }

    @Override
    public void onItemListClicked(Project project) {

    }

    @Override
    public void onStartDateClicked(int flag) {
        timeDateFlag = flag;
    }

    @Override
    public void onEndDateClicked(int flag) {
        timeDateFlag = flag;
    }

    @Override
    public void onStartTimeClicked(int flag) {
        timeDateFlag = flag;
    }

    @Override
    public void onEndTimeClicked(int flag) {
        timeDateFlag = flag;
    }

    public static final String TAG = NoteHomeActivity.class.getSimpleName();

    @Override
    public void onDateSelected(int year, int month, int day) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);
        String date = String.format("%4d-%02d-%02d", year, month, day);
        if (currentFragment instanceof AddNewProjectFragment) {
            addNewProjectFragment  = (AddNewProjectFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);
            if (timeDateFlag == Constant.START_DATE) {
                addNewProjectFragment.setStartDate(date);
                Log.d(TAG, "onDateSelected: " + year);
            } else if (timeDateFlag == Constant.END_DATE) {
                addNewProjectFragment.setEndDate(date);
                Log.d(TAG, "onDateSelected: " + year);
            }
        } else if (currentFragment instanceof AddNewTaskFragment) {
            addNewTaskFragment  = (AddNewTaskFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);

            if (timeDateFlag == Constant.START_DATE) {
                addNewTaskFragment.setStartDate(date);
                Log.d(TAG, "onDateSelected: " + year);
            } else if (timeDateFlag == Constant.END_DATE) {
                addNewTaskFragment.setEndDate(date);
                Log.d(TAG, "onDateSelected: " + year);
            }
        }

    }

    @Override
    public void onTimeSelected(int hour, int minute) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);
        String time = String.format("%02d:%02d", hour, minute);
        if (currentFragment instanceof AddNewProjectFragment) {
            addNewProjectFragment  = (AddNewProjectFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);

            if (timeDateFlag == Constant.START_TIME) {
                addNewProjectFragment.setStartTime(time);
            } else if (timeDateFlag == Constant.END_TIME) {
                addNewProjectFragment.setEndTime(time);
            }
        } else if (currentFragment instanceof AddNewTaskFragment) {
            addNewTaskFragment  = (AddNewTaskFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_holder_home);
            if (timeDateFlag == Constant.START_TIME) {
                addNewTaskFragment.setStartTime(time);
            } else if (timeDateFlag == Constant.END_TIME) {
                addNewTaskFragment.setEndTime(time);
            }
        }

    }


}
