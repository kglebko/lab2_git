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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class Fragment1 extends Fragment {

    private DatePicker startDatePicker, endDatePicker;
    private Button btnShow;
    private TextView tvResult;

    public Fragment1() {} // обязательный пустой конструктор

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        startDatePicker = view.findViewById(R.id.startDatePicker);
        endDatePicker = view.findViewById(R.id.endDatePicker);
        btnShow = view.findViewById(R.id.btnShow);
        tvResult = view.findViewById(R.id.tvResult);

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

        return view;
    }
}
