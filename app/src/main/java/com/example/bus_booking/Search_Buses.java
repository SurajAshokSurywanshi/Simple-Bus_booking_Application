package com.example.bus_booking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bus_booking.Model.Bus_Search_Model;
import com.example.bus_booking.Model.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Search_Buses extends AppCompatActivity {
    ProgressDialog progressDialog11;
    private RequestQueue mRequestQueue;
    RecyclerView recyclerView_bus_search;
    public Bus_Serach_Adapter bus_serach_adapter;
    private String strDate;
    private SimpleDateFormat dateFormat;
    private ArrayList<Bus_Search_Model.BusSearchResult> mExampleList1;
    List< Bus_Search_Model.BusSearchResult> busSearchResults;
    ProgressDialog progressDialog;

    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_buses);
        recyclerView_bus_search = findViewById(R.id.recyclerView_serchbuses);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_bus_search.setLayoutManager(layoutManager);

       // mExampleList = new ArrayList<>();
        busSearchResults = new ArrayList<>();
        getData();
        mRequestQueue = Volley.newRequestQueue(this);

    }


    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // JSONObject object = new JSONObject();
        HttpsTrustManager.allowAllSSL();
        // Enter the correct url for your api service site
        String url = "https://202.143.96.44:1831/api/Mob/Get_Bus_Search";//getResources().getString(R.string.url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog = ProgressDialog.show(Search_Buses.this, "Loading Data", "Please Wait...", false, false);

                        try {

                            JSONArray jsonArray = response.getJSONArray("BusSearchResult");
                            Log.d("ArrayResult11=",""+jsonArray);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                if (hit != null) {
                                    Bus_Search_Model.BusSearchResult BussearchList1 = new Bus_Search_Model.BusSearchResult("BusSearchResult");

                                    BussearchList1.setBusType(hit.getString("BusType").toString());
                                    BussearchList1.setTravelName(hit.getString("TravelName").toString());
                                    BussearchList1.setArrivalTime(hit.getString("ArrivalTime").toString());
                                    BussearchList1.setDepartureTime(hit.getString("DepartureTime").toString());
                                    BussearchList1.setAvailableSeats(hit.getLong("AvailableSeats"));
                                    BussearchList1.setPrice(hit.getString("AvailableFares"));

                                    busSearchResults.add(BussearchList1);
                                    bus_serach_adapter = new Bus_Serach_Adapter(getApplicationContext(), (ArrayList<Bus_Search_Model.BusSearchResult>) busSearchResults);

                                    recyclerView_bus_search.setAdapter(bus_serach_adapter);
                                }
                                progressDialog.dismiss();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {

                Log.d("ErrorTAG","Error"+error);


            }

        });
        requestQueue.add(jsonObjectRequest);

    }



    public class Bus_Serach_Adapter extends RecyclerView.Adapter<Bus_Serach_Adapter.ViewHolder> {
        LayoutInflater layoutInflater;
        List< Bus_Search_Model.BusSearchResult> Buslist1;
        String searchString = "";
        private ArrayList<Bus_Search_Model.BusSearchResult> arraylist;
        private Context mContext;


        public Bus_Serach_Adapter(Context context, ArrayList<Bus_Search_Model.BusSearchResult> Buslist1) {
            mContext = context;
            this.Buslist1 = Buslist1;
            this.arraylist = new ArrayList<Bus_Search_Model.BusSearchResult>();
        }



        @NonNull
        @Override
        public Bus_Serach_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.search_buses_layout, parent, false);
            return new Bus_Serach_Adapter.ViewHolder(v);
        }



        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull Bus_Serach_Adapter.ViewHolder holder, int position) {

            holder.Seats.setText(String.valueOf(Buslist1.get(position).getAvailableSeats()));
            holder.Bustype.setText(Buslist1.get(position).getBusType());
            holder.BusTravName.setText(Buslist1.get(position).getTravelName());
            holder.tv_rate.setText(Buslist1.get(position).getPrice());


            //  String text = "/Date(1543469400000)/";
            //String text = "/\\Date(1543509000000)\\/";//  holder.ArravialTime.setText(sss);

            String ArravialTimes = Buslist1.get(position).getArrivalTime();
            Instant instant = Instant.ofEpochMilli(Long.parseLong(ArravialTimes.replaceAll("\\D+", "")));
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

            String DepteratTime = Buslist1.get(position).getDepartureTime();
            Instant instant1 = Instant.ofEpochMilli(Long.parseLong(DepteratTime.replaceAll("\\D+", "")));
            ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());



            // A custom format
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("  hh:mm a", Locale.getDefault());
            String strDateTimeFormatted = zdt.format(dtf);
            holder.ArravialTime.setText(strDateTimeFormatted);

            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("  hh:mm a", Locale.getDefault());
            String strDateTimeFormatted1 = zdt1.format(dtf1);
            System.out.println(strDateTimeFormatted);
            holder.DepteratTime.setText(strDateTimeFormatted1);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date startDate = null;
            try {
                startDate = simpleDateFormat.parse(strDateTimeFormatted1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date endDate = null;
            try {
                endDate = simpleDateFormat.parse(strDateTimeFormatted);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long difference = endDate.getTime() - startDate.getTime();
            if(difference<0)
            {
                Date dateMax = null;
                try {
                    dateMax = simpleDateFormat.parse("24:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dateMin = null;
                try {
                    dateMin = simpleDateFormat.parse("00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
            }
            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            Log.d("timefjnjvjfsb","Hours: "+hours+", Mins: "+min);
            holder.Duration.setText(""+hours+"h :  "+min+"m");

        }

        @Override
        public int getItemCount() {
            return Buslist1.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView Bustype,BusTravName,DepteratTime,ArravialTime,Seats,tv_rate,Duration;
            ImageView img_icon;

            public ViewHolder(View view) {
                super(view);
                Bustype = view.findViewById(R.id.tv_travel_type);
                BusTravName = view.findViewById(R.id.bustraveals_name);
                DepteratTime = view.findViewById(R.id.departur_time);
                ArravialTime = view.findViewById(R.id.arrival_time);
                Seats = view.findViewById(R.id.noOfbusSeats);
                tv_rate = view.findViewById(R.id.tv_rate);
                Duration = view.findViewById(R.id.duration);


            }
        }
    }


}