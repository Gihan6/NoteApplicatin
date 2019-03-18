package com.creativematrix.noteapp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;

import com.creativematrix.noteapp.callback.DateTimeCallbacks;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    DateTimeCallbacks mCallback;

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
        mCallback.onDateSelected(y, m, d);
    }

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
        DatePickerDialog dialog;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;
    }
}
