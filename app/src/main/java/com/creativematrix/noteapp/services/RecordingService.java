package com.creativematrix.noteapp.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.PendingTaskActivity;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.app.NotificationCompat;

/**
 * Created by Daniel on 12/28/2014.
 */
public class RecordingService extends Service {

    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    //public FileInterface FileInterface;
    private long mStartingTimeMillis = 0;
    private long mElapsedMillis = 0;
    private int mElapsedSeconds = 0;
    private OnTimerChangedListener onTimerChangedListener = null;
    private static final SimpleDateFormat mTimerFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());

    private Timer mTimer = null;
    private TimerTask mIncrementTimerTask = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public interface OnTimerChangedListener {
        void onTimerChanged(int seconds);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        //  mDatabase = new DBHelper(getApplicationContext());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording();
        }


        super.onDestroy();
    }

    public void startRecording() {
        setFileNameAndPath();
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);
        /*if (MySharedPreferences.getPrefHighQuality(this)) {
            mRecorder.setAudioSamplingRate(44100);
            mRecorder.setAudioEncodingBitRate(192000);
        }*/

        try {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();

            //startTimer();

            //startForeground(1, createNotification());

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void setFileNameAndPath() {
        int count = 0;
        File f;
        do {
            count++;
            mFileName = getString(R.string.default_file_name)
                    + "_" + (count) + ".mp3";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += "/NoteApp/" + mFileName;
            f = new File(mFilePath);
        } while (f.exists() && !f.isDirectory());
    }

    public void stopRecording() {
        mRecorder.stop();
        mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        mRecorder.release();


        //remove notification
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }

        mRecorder = null;

        try {
            PendingTaskActivity pendingTaskActivity= new PendingTaskActivity();
            pendingTaskActivity.setFileName(mFilePath);


        } catch (Exception e) {
            Log.e(LOG_TAG, "exception", e);
        }
    }

    private void startTimer() {
        mElapsedSeconds = 0;
        mTimer = new Timer();
        mIncrementTimerTask = new TimerTask() {
            @Override
            public void run() {

                if (mElapsedSeconds == 20)
                    stopRecording();

                mElapsedSeconds++;

                if (onTimerChangedListener != null)
                    onTimerChangedListener.onTimerChanged(mElapsedSeconds);
            }
        };
        mTimer.scheduleAtFixedRate(mIncrementTimerTask, 1000, 1000);
    }

    //TODO:

    public interface FileInterface {

        void setFileName(String FileName);

    }
}
