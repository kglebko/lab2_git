package com.example.lab_git;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {

    private DatePicker startDatePicker, endDatePicker;
    private Button btnShow;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult);

        btnShow.setOnClickListener(v -> {
            LocalDate startDate = LocalDate.of(
                    startDatePicker.getYear(),
                    startDatePicker.getMonth() + 1,
                    startDatePicker.getDayOfMonth()
            );

            LocalDate endDate = LocalDate.of(
                    endDatePicker.getYear(),
                    endDatePicker.getMonth() + 1,
                    endDatePicker.getDayOfMonth()
            );

            StringBuilder schedule = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                if (date.getDayOfWeek() == DayOfWeek.TUESDAY) {
                    schedule.append(date.format(formatter)).append(" (вт) — 15:30\n");
                } else if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    schedule.append(date.format(formatter)).append(" (пт) — 17:00\n");
                }
            }

            if (schedule.length() == 0) {
                tvResult.setText("В выбранный интервал занятий нет.");
            } else {
                tvResult.setText(schedule.toString());
            }
        });
    }
}

