package com.example.bus_booking;

import static com.example.bus_booking.BusList.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    LinearLayout Fromlist, CallinearLayout2,ToList;
    Button send;
    FloatingActionButton SwapButton;

    int year, month, day;
    TextView departuredate, tv_bus_tomorrow, tv_bus_today,city,bs_destination;
    private Date today;
    private Date tomorrow;
    private String todayAsString;
    private String tomorrowAsString;
    SharedPreferences preferences;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        departuredate = findViewById(R.id.departuredate);
        tv_bus_today = findViewById(R.id.tv_bus_today);
        tv_bus_tomorrow = findViewById(R.id.tv_bus_tomorrow);
        Fromlist = findViewById(R.id.fl_source);
        CallinearLayout2 = findViewById(R.id.linear_date_departure);
        send = findViewById(R.id.search_Bus);
        ToList = findViewById(R.id.bs_destination);
        SwapButton =findViewById(R.id.BusSwap);
        // final Calendar calendar = Calendar.getInstance();
        city= findViewById(R.id.city);
        bs_destination = findViewById(R.id.dest_cityname);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");

        city.setText(value);
/*
        bs_destination.setText(value111);
*/


        Fromlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BusList.class);
                startActivity(intent);
            }
        });
        ToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BusList.class);
                startActivity(intent);
            }
        });


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        today = calendar.getTime();

        //calendarPickerView.setOnDateSelectedListener(new C04622());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        todayAsString = sdf.format(today);
        tomorrowAsString = sdf.format(tomorrow);

        departuredate.setText(todayAsString);
        tv_bus_today.setTextColor(getApplication().getResources().getColor(R.color.colorPrimary));

        CallinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                departuredate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        tv_bus_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                departuredate.setText(todayAsString);
                tv_bus_today.setTextColor(getApplication().getResources().getColor(R.color.colorPrimary));
                tv_bus_tomorrow.setTextColor(getApplication().getResources().getColor(R.color.gray_bus));

            }
        });

        tv_bus_tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departuredate.setText(tomorrowAsString);
                tv_bus_today.setTextColor(getApplication().getResources().getColor(R.color.gray_bus));
                tv_bus_tomorrow.setTextColor(getApplication().getResources().getColor(R.color.colorPrimary));
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Search_Buses.class);
                startActivity(intent);
            }
        });

        SwapButton.setOnClickListener(new View.OnClickListener() {
       @Override
        public void onClick(View v) {
         if (city.getText().toString().isEmpty() || bs_destination.getText().toString().isEmpty()) {
             Toast.makeText(getApplicationContext(), "Please select origin or destination !", Toast.LENGTH_SHORT).show();
         } else {
             RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
             rotate.setDuration(500);
             rotate.setInterpolator(new LinearInterpolator());
             SwapButton.startAnimation(rotate);
             temp = city.getText().toString();
             city.setText(bs_destination.getText().toString());
             bs_destination.setText(temp);
         }
     }
 });


    }
           // calendar.setOnDateSelectedListener(new C04622());





}
