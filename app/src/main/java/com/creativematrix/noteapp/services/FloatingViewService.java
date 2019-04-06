package com.creativematrix.noteapp.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aykuttasil.callrecord.CallRecord;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.MainActivity;
import com.creativematrix.noteapp.util.Utils;
import com.melnykov.fab.FloatingActionButton;

import java.io.File;

public class FloatingViewService extends Service {

    private WindowManager mWindowManager;
    private View mFloatingView;
    CallRecord callRecord;
    ImageView cancel_voice, play_voice;
    Button btnAddToCart;
    private FloatingActionButton mRecordButton = null;
    private int mRecordPromptCount = 0;
    Intent intent;
    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;
    private Chronometer mChronometer = null;
    LinearLayout linear_layout_play;
    long timeWhenPaused = 0; //stores time when user clicks pause button
    long elapsedMillis;
    private TextView mRecordingPrompt;

    public FloatingViewService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Inflate the floating view layout we created
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        linear_layout_play = mFloatingView.findViewById(R.id.linear_layout_play);

         mRecordingPrompt= (TextView) mFloatingView.findViewById(R.id.recording_status_text);

        cancel_voice = mFloatingView.findViewById(R.id.cancel_voice);
        play_voice = mFloatingView.findViewById(R.id.play_voice);
        btnAddToCart = mFloatingView.findViewById(R.id.btnAddToCart);
        mChronometer = (Chronometer) mFloatingView.findViewById(R.id.chronometer);


        cancel_voice.setOnClickListener(new View./*
        play_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product tempObj = new Product("3", "Record", "Record", "", "", "", "", "", mFileName, "", false);

                if (tempObj.getProductName_EN().equals("Record")) {

                    PlaybackFragment playbackFragment =
                            new PlaybackFragment().newInstance(tempObj);

                    FragmentTransaction transaction = ((FragmentActivity) getActivity())
                            .getSupportFragmentManager()
                            .beginTransaction();

                    playbackFragment.show(transaction, "dialog_playback");
                }
            }
        });
*/
                OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_layout_play.setVisibility(View.INVISIBLE);

            }
        });
        mRecordButton = (FloatingActionButton) mFloatingView.findViewById(R.id.btnRecord);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        //The root element of the collapsed view layout
        final View collapsedView = mFloatingView.findViewById(R.id.collapse_view);
        //The root element of the expanded view layout
        final View expandedView = mFloatingView.findViewById(R.id.expanded_container);


        //Set the close button
        ImageView closeButtonCollapsed = mFloatingView.findViewById(R.id.close_btn);
        closeButtonCollapsed.setOnClickListener(view -> {
            //close the service and remove the from from the window
            stopSelf();
        });

        //Set the view while floating view is expanded.
        //Set the play button.
        ImageView start_record_btn = mFloatingView.findViewById(R.id.start_record_btn);
        start_record_btn.setOnClickListener(v ->
                callRecord.startCallReceiver()
        );


        //Set the next button.
        ImageView close_record_btn = mFloatingView.findViewById(R.id.close_record_btn);
        close_record_btn.setOnClickListener(v -> Utils.showStringToast(FloatingViewService.this, getResources().getString(R.string.stop_record)));

        //Set the pause button.
        ImageView prevButton = (ImageView) mFloatingView.findViewById(R.id.prev_btn);
        prevButton.setOnClickListener(v -> Toast.makeText(FloatingViewService.this, "Playing previous song.", Toast.LENGTH_LONG).show());

        //Set the close button
        ImageView closeButton = (ImageView) mFloatingView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> {
            collapsedView.setVisibility(View.VISIBLE);
            expandedView.setVisibility(View.GONE);
        });

        //Open the application on thi button click
        ImageView openButton = (ImageView) mFloatingView.findViewById(R.id.open_button);
        openButton.setOnClickListener(view -> {
            //Open the application  click.
            // Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
            //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //  startActivity(intent);

            //close the service and remove view from the view hierarchy
            //  stopSelf();
        });

        //Drag and move floating view using user's touch action.
        mFloatingView.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Detect if the floating view is collapsed or expanded.
     *
     * @return true if the floating view is collapsed.
     */
    private void onRecord(boolean start) {
        intent = new Intent(FloatingViewService.this, RecordingService.class);
        if (start) {
            linear_layout_play.setVisibility(View.INVISIBLE);
            // start recording
            mRecordButton.setImageResource(R.drawable.ic_stop);
            //mPauseButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, this.getResources().getString(R.string.toast_recording_start), Toast.LENGTH_SHORT).show();
            File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                folder.mkdir();
            }

            //start Chronometer
            mChronometer.setBase(SystemClock.elapsedRealtime());

            mChronometer.start();

            mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
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
            linear_layout_play.setVisibility(View.VISIBLE);
            mRecordButton.setImageResource(R.drawable.ic_mic);

            //mPauseButton.setVisibility(View.GONE);
            mChronometer.stop();
            mChronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));

           this.stopService(intent);
            //allow the screen to turn off again once recording is finished
            // getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        }
    }

    private boolean isViewCollapsed() {
        return mFloatingView == null || mFloatingView.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }
    private void showElapsedTime() {
        elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        int seconds = (int) (elapsedMillis / 1000) % 60;
        if (seconds == 30) {

            mRecordButton.setImageResource(R.drawable.ic_mic);
            // mPauseButton.setVisibility(View.GONE);
            mChronometer.stop();
            mChronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));
            // getActivity().stopService(intent);
           this.stopService(intent);
            linear_layout_play.setVisibility(View.VISIBLE);
            mRecordButton.setImageResource(R.drawable.ic_mic);
            mStartRecording = true;
            /*Toasty.info(getActivity(), "Only 60 seconds Per record permitted " + elapsedMillis,
                    Toast.LENGTH_SHORT).show();*/
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }
}
