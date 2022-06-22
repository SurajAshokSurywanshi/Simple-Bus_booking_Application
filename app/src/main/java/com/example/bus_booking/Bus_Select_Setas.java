package com.example.bus_booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Bus_Select_Setas extends AppCompatActivity {

    LinearLayout layout_tab;
    TextView tvLower;
    TextView tvUpper;
    TextView tvSeatsSelected;
    TextView tv_totalFare;
    Button btn_booknow;
    TableLayout tblSeatLayout;
    TableLayout tblSeatLayout1;
    TextView tv_seat_avail;
    TextView tv_seat_selected;
    TextView tv_seat_ladies_reserved;
    TextView tv_seat_ladies_booked;
    TextView tv_seat_common_booked;ImageView iv_seat_avail;ImageView iv_seat_selected;
    ImageView iv_seat_ladies_reserved;
    ImageView iv_seat_ladies_booked;
    ImageView iv_seat_common_booked;

    private String strBusName;
    private String strBusType_;
    private String strDate;
    private TextView txtSeatNo;
    private Drawable availableSeat;
    private Drawable bookedSeat;
    private Drawable selectedSeat;
    double child_fare = 0.0d;
    double fare = 0.0d;
    int noOfSeats = 0;

    double totalFare = 0.0d;
    private String strBusType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_select_setas);
        layout_tab = findViewById(R.id.layout_tab);
        tvLower = findViewById(R.id.tvLower);
        tvUpper = findViewById(R.id.tvUpper);
        tvSeatsSelected = findViewById(R.id.tvSeatsSelected);
        tv_totalFare = findViewById(R.id.tv_totalFare);
        btn_booknow = findViewById(R.id.btn_booknow);
        tblSeatLayout = findViewById(R.id.tblSeatLayout);
        tblSeatLayout1 = findViewById(R.id.tblSeatLayout1);
        tv_seat_avail = findViewById(R.id.tv_seat_avail);
        tv_seat_selected = findViewById(R.id.tv_seat_selected);
        tv_seat_ladies_reserved = findViewById(R.id.tv_seat_ladies_reserved);
        tv_seat_ladies_booked = findViewById(R.id.tv_seat_ladies_booked);
        tv_seat_common_booked = findViewById(R.id.tv_seat_common_booked);
        iv_seat_avail = findViewById(R.id.iv_seat_avail);
        iv_seat_selected = findViewById(R.id.iv_seat_selected);
        iv_seat_ladies_reserved = findViewById(R.id.iv_seat_ladies_reserved);
        iv_seat_ladies_booked = findViewById(R.id.iv_seat_ladies_booked);
        iv_seat_common_booked = findViewById(R.id.iv_seat_common_booked);



        tv_seat_avail.setText("Available \nSeat");
        tv_seat_selected.setText("Selected \nSeat");
        tv_seat_ladies_reserved.setText("Reserved for \nLadies");
        tv_seat_ladies_booked.setText("Booked by \nLadies");
        tv_seat_common_booked.setText("Booked \nSeat");


       /* if (strBusType_.equals("SLEEPER")) {
            layout_tab.setVisibility(View.VISIBLE);

            iv_seat_avail.setImageDrawable(getResources().getDrawable(R.drawable.sleeper_available));
            iv_seat_selected.setImageDrawable(getResources().getDrawable(R.drawable.sleeper_selected));
            iv_seat_ladies_reserved.setImageDrawable(getResources().getDrawable(R.drawable.bus_ladies_sleeper_available));
            iv_seat_ladies_booked.setImageDrawable(getResources().getDrawable(R.drawable.bus_ladies_sleeper_selected));
            iv_seat_common_booked.setImageDrawable(getResources().getDrawable(R.drawable.sleeper_booked_new));
            //prepareSleeperData("lower");
        } else {
            layout_tab.setVisibility(View.GONE);

            iv_seat_avail.setImageDrawable(getResources().getDrawable(R.drawable.seater_seat_available));
            iv_seat_selected.setImageDrawable(getResources().getDrawable(R.drawable.seat_selected_ui));
            iv_seat_ladies_reserved.setImageDrawable(getResources().getDrawable(R.drawable.seat_ladies_available));
         //   iv_seat_ladies_booked.setImageDrawable(getResources().getDrawable(R.drawable.bus_ladies_seat_selected));
            iv_seat_common_booked.setImageDrawable(getResources().getDrawable(R.drawable.seat_booked));
           // prepareSeaterData();
        }*/

        tvLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLower.setBackgroundResource(R.drawable.edittextbrodercolorprimary);
                tvLower.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                tvUpper.setBackgroundResource(R.drawable.edittextbrodercolorwhite);
                tvUpper.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
               // prepareSleeperData("lower");

            }
           });

        tvUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvUpper.setBackgroundResource(R.drawable.edittextbrodercolorprimary);
                tvUpper.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                tvLower.setBackgroundResource(R.drawable.edittextbrodercolorwhite);
                tvLower.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            //    prepareSleeperData("upper");

            }
          });

        btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noOfSeats == 0) {
                    Toast.makeText(getApplicationContext(), "Please select atleast one seat !", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("NoofPassangers", String.valueOf(noOfSeats));
                  //  changeFragmentTo(BusBoardingDropping.class.getSimpleName(), true, bundle1);
                }
            }
            });


    }
}