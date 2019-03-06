package com.creativematrix.noteapp.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import com.creativematrix.noteapp.callback.DateTimeCallbacks;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    DateTimeCallbacks mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (DateTimeCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TimePickerDialog dialog;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
        int min = calendar.get(Calendar.MINUTE);
        dialog = new TimePickerDialog(getActivity(), this, hour, min, true);

        return dialog;
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        mCallback.onTimeSelected(h, m);
    }
}