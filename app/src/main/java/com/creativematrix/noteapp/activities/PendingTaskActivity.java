package com.creativematrix.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.task.FilesBinary;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.fragments.PlaybackFragment;
import com.creativematrix.noteapp.services.FloatingViewService;
import com.creativematrix.noteapp.services.RecordingService;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PendingTaskActivity extends AppCompatActivity implements RecordingService.FileInterface {
    Intent intent;
    TextInputEditText input_task_name, input_task_description;
    LinearLayout play_cancel_layout;
    Chronometer chronometer;
    private TextView mRecordingPrompt;
    long timeWhenPaused = 0; //stores time when user clicks pause button
    private int mRecordPromptCount = 0;
    Button cancel_voice, play_voice;
    public static String mFileName;
    Button btnSaveTask;
    long elapsedMillis;
    private List<FilesBinary> filesBinaryList = new ArrayList<>();
    RecordView recordView;
    RecordButton recordButton;
    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;
    com.melnykov.fab.FloatingActionButton btnRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_task);
        configureViews();
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });
        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    collectData();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        play_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaybackFragment playbackFragment =
                        new PlaybackFragment().newInstance(mFileName);

                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();

                playbackFragment.show(transaction, "dialog_playback");
            }
        });
        cancel_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_cancel_layout.setVisibility(View.INVISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
                btnSaveTask.setVisibility(View.GONE);
            }
        });

    }

    private void collectData() throws JsonProcessingException {
        String taskName = input_task_name.getText().toString();
        String taskDesc = input_task_description.getText().toString();


        if (Utils.isFieldEmpty(taskName)) {
            Utils.showResToast(this, R.string.task_name_empty);
            return;
        }
        if (Utils.isFieldEmpty(mFileName)) {
            Utils.showResToast(this, R.string.task_voice_note_empty);
            return;
        }
        /*if (Utils.isFieldEmpty(taskDesc)) {
            Utils.showResToast(this, R.string.empt);
            return;
        }*/


        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskDescripation(taskDesc);
        task.setCompanyID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(this)));
        task.setAddedID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(this)));
        task.setPending(true);
        task.setFilesBinaryList(filesBinaryList);

        String filename = mFileName.substring(mFileName.lastIndexOf("/") + 1);
        String fileExtension = mFileName.substring(mFileName.lastIndexOf(".") + 1);
        String fileContent = null;
        fileContent = Utils.getFileBinary(mFileName);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        filesBinaryList.add(new FilesBinary(filename, fileExtension, fileContent));
        task.setFilesBinaryList(filesBinaryList);
        String json = ow.writeValueAsString(task);
        new TaskRepo(this).addTask(task)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(this, getString(R.string.task_added_success));
                            this.onBackPressed();
                        } else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)) {
                            Utils.showStringToast(this, GroupRes.getMsg().toString());

                        }
                    } catch (Exception ex) {

                    }
                });

    }

    private void configureViews() {
        input_task_name = findViewById(R.id.input_task_name);
        input_task_description = findViewById(R.id.input_task_description);
        btnRecord = findViewById(R.id.btnRecord);
        play_cancel_layout = findViewById(R.id.play_cancel_layout);
        chronometer = findViewById(R.id.chronometer);
        mRecordingPrompt = findViewById(R.id.recording_status_text);
        cancel_voice = findViewById(R.id.cancel_voice);
        play_voice = findViewById(R.id.play_voice);
        btnSaveTask = findViewById(R.id.btn_save_task);
        recordView = findViewById(R.id.record_view);
        recordButton = findViewById(R.id.record_button);

        //IMPORTANT
        recordButton.setRecordView(recordView);
        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                //Start Recording..
                Log.d("RecordView", "onStart");
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }

            @Override
            public void onCancel() {
                //On Swipe To Cancel
                Log.d("RecordView", "onCancel");

                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenPaused = 0;
                mRecordingPrompt.setText(getString(R.string.record_prompt));
                stopService(intent);
                btnRecord.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFinish(long recordTime) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
                //Stop Recording..
               // String time = getHumanTimeText(recordTime);
                Log.d("RecordView", "onFinish");

             //   Log.d("RecordTime", time);
            }

            @Override
            public void onLessThanSecond() {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenPaused = 0;
                mRecordingPrompt.setText(getString(R.string.record_prompt));
             //   stopService(intent);
                btnRecord.setVisibility(View.INVISIBLE);
                mStartRecording = !mStartRecording;
                //When the record time is less than One Second
                Log.d("RecordView", "onLessThanSecond");
            }
        });
    }

    private void showElapsedTime() {
        elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int seconds = (int) (elapsedMillis / 1000) % 60;
        if (seconds == 30) {

            btnRecord.setImageResource(R.drawable.ic_mic);
            // mPauseButton.setVisibility(View.GONE);
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));
            // getActivity().stopService(intent);
            this.stopService(intent);
            play_cancel_layout.setVisibility(View.VISIBLE);
            btnRecord.setImageResource(R.drawable.ic_mic);
            mStartRecording = true;
            /*Toasty.info(getActivity(), "Only 60 seconds Per record permitted " + elapsedMillis,
                    Toast.LENGTH_SHORT).show();*/
        }

    }

    private void onRecord(boolean start) {
        intent = new Intent(PendingTaskActivity.this, RecordingService.class);
        if (start) {
            btnSaveTask.setVisibility(View.GONE);
            play_cancel_layout.setVisibility(View.INVISIBLE);
            // start recording
            btnRecord.setImageResource(R.drawable.ic_stop);
            //mPauseButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, this.getResources().getString(R.string.toast_recording_start), Toast.LENGTH_SHORT).show();
            File folder = new File(Environment.getExternalStorageDirectory() + "/NoteApp");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                folder.mkdir();
            }

            //start Chronometer
            chronometer.setBase(SystemClock.elapsedRealtime());

            chronometer.start();

            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    showElapsedTime();
                    if (mRecordPromptCount == 0) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
                    } else if (mRecordPromptCount == 1) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "..");
                    } else if (mRecordPromptCount == 2) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "...");
                        mRecordPromptCount = -1;
                    }

                    mRecordPromptCount++;
                }


            });
         /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.startForegroundService(intent);
            } else {
                this.startService(intent);
            }*/
            //start RecordingService
            this.startService(intent);
            //keep screen on while recording
            //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
            mRecordPromptCount++;


         /*   Product product = new Product("Record","" , "", "", "", "", "", "", "");
            CenterRepository.getCenterRepository()
                    .getListOfProductsInShoppingList().add(product);
            ((HomeActivity) getContext())
                    .updateItemCount(true);*/
        } else {
            //stop recording
            btnSaveTask.setVisibility(View.VISIBLE);
            play_cancel_layout.setVisibility(View.VISIBLE);
            btnRecord.setImageResource(R.drawable.ic_mic);
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));
            this.stopService(intent);
            btnRecord.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void setFileName(String FileName) {
        this.mFileName = FileName;
    }
}
