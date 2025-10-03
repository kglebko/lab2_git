package com.example.lab_git;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    DatePicker datePicker;
    Button btnCheck;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        datePicker = findViewById(R.id.datePicker);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);

        Calendar today = Calendar.getInstance();
        datePicker.updateDate(today.get(Calendar.YEAR), 0, 1);

        int daySpinnerId = getResources().getIdentifier("day", "id", "android");
        if (daySpinnerId != 0) {
            datePicker.findViewById(daySpinnerId).setVisibility(View.GONE);
        }

        int monthSpinnerId = getResources().getIdentifier("month", "id", "android");
        if (monthSpinnerId != 0) {
            datePicker.findViewById(monthSpinnerId).setVisibility(View.GONE);
        }

        btnCheck.setOnClickListener(v -> {
            int year = datePicker.getYear();
            int sundays = countSundaysInYear(year);
            tvResult.setText("В " + year + " году будет " + sundays + " воскресений.");
        });
    }

    private int countSundaysInYear(int year) {
        int sundays = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(year, Calendar.JANUARY, 1);

        int currentYear = cal.get(Calendar.YEAR);
        while (cal.get(Calendar.YEAR) == currentYear) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays++;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return sundays;
    }
}
