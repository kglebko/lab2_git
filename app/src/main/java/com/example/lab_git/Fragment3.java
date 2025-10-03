package com.example.lab_git;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Fragment3 extends Fragment {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText inputHours, inputMinutes;
    private Button btnCalculate;
    private TextView tvResult;

    public Fragment3() {
        // пустой конструктор обязателен для фрагментов
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        datePicker = view.findViewById(R.id.datePicker);
        timePicker = view.findViewById(R.id.timePicker);
        inputHours = view.findViewById(R.id.inputHours);
        inputMinutes = view.findViewById(R.id.inputMinutes);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        tvResult = view.findViewById(R.id.tvResult);

        timePicker.setIs24HourView(true);

        btnCalculate.setOnClickListener(v -> {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, hour, minute);

            int addHours = 0, addMinutes = 0;
            try {
                addHours = Integer.parseInt(inputHours.getText().toString());
            } catch (Exception ignored) {}

            try {
                addMinutes = Integer.parseInt(inputMinutes.getText().toString());
            } catch (Exception ignored) {}

            calendar.add(Calendar.HOUR_OF_DAY, addHours);
            calendar.add(Calendar.MINUTE, addMinutes);
            Date newDate = calendar.getTime();

            LocalDateTime localDateTime = newDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String result = localDateTime.format(formatter);

            tvResult.setText("Результат: " + result);
        });

        return view;
    }
}
