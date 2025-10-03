package com.example.lab_git;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Fragment2 extends Fragment {

    private DatePicker datePicker;
    private Button btnCheck;
    private TextView tvResult;

    public Fragment2() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main2, container, false);

        datePicker = view.findViewById(R.id.datePicker);
        btnCheck = view.findViewById(R.id.btnCheck);
        tvResult = view.findViewById(R.id.tvResult);

        Calendar today = Calendar.getInstance();
        datePicker.updateDate(today.get(Calendar.YEAR), 0, 1);

        int daySpinnerId = getResources().getIdentifier("day", "id", "android");
        if (daySpinnerId != 0) datePicker.findViewById(daySpinnerId).setVisibility(View.GONE);

        int monthSpinnerId = getResources().getIdentifier("month", "id", "android");
        if (monthSpinnerId != 0) datePicker.findViewById(monthSpinnerId).setVisibility(View.GONE);

        btnCheck.setOnClickListener(v -> {
            int year = datePicker.getYear();
            int sundays = countSundaysInYear(year);
            tvResult.setText("В " + year + " году будет " + sundays + " воскресений.");
        });

        return view;
    }

    private int countSundaysInYear(int year) {
        int sundays = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(year, Calendar.JANUARY, 1);

        int currentYear = cal.get(Calendar.YEAR);
        while (cal.get(Calendar.YEAR) == currentYear) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) sundays++;
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return sundays;
    }
}
