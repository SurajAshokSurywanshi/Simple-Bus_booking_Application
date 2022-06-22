package com.example.bus_booking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarActivity2 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        EditText editText = findViewById(R.id.in_date);
        TextView result = findViewById(R.id.resultText);
        TextView result1 = findViewById(R.id.resultText1);
        TextView result2 = findViewById(R.id.resultText2);


        String milli = String.valueOf(System.currentTimeMillis());
        editText.setText(milli);

        String text = "/Date(1543469400000)/";
        Instant instant = Instant.ofEpochMilli(Long.parseLong(text.replaceAll("\\D+", "")));
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

        // A custom format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" hh:mm a", Locale.getDefault());
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(" dd MMM yyyy", Locale.getDefault());
        String strDateTimeFormatted = zdt.format(dtf);
        String strDateTimeFormatted1 = zdt.format(dtf1);
        System.out.println(strDateTimeFormatted);
        result1.setText(strDateTimeFormatted);
        result2.setText(strDateTimeFormatted1);



        findViewById(R.id.btn_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   long etMills = Long.parseLong(editText.getText().toString());
                long etMills = Long.parseLong("1543469400000");

                SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                SimpleDateFormat simpleTimeFormat  = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

                String date = simpleDateFormat.format(etMills);
                String time = simpleTimeFormat.format(etMills);


                result.setText(date+" - - - "+time);


            }
        });
    }

}