package com.example.lab_git;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText inputHours, inputMinutes;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        inputHours = findViewById(R.id.inputHours);
        inputMinutes = findViewById(R.id.inputMinutes);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        timePicker.setIs24HourView(true);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();


                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);
                Date date = calendar.getTime();


                int addHours = 0, addMinutes = 0;
                try {
                    addHours = Integer.parseInt(inputHours.getText().toString());
                }
                catch (Exception ignored) {}

                try {
                    addMinutes = Integer.parseInt(inputMinutes.getText().toString());
                }
                catch (Exception ignored) {}


                calendar.add(Calendar.HOUR_OF_DAY, addHours);
                calendar.add(Calendar.MINUTE, addMinutes);
                Date newDate = calendar.getTime();

                // форматированный вывод DateTimeFormatter
                LocalDateTime localDateTime = newDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

                String result = localDateTime.format(formatter);


                tvResult.setText("Результат: " + result);
            }
        });
    }
}