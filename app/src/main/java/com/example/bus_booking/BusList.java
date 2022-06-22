package com.example.bus_booking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bus_booking.Model.Bus_Model;
import com.example.bus_booking.Model.Bus_Search_Model;
import com.example.bus_booking.Model.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BusList extends AppCompatActivity  implements SearchView.OnQueryTextListener{
     BusListAdapter busListAdapter;
   // AirportListAdapter airportListAdapter;
    RecyclerView recyclerView;
        ProgressDialog progressDialog;
        TextView txtError;
        Button fetcData;
        private String hint;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public  SharedPreferences pref;
    private String tag;
    SearchView editsearch;
   private RequestQueue mRequestQueue;
    List< Bus_Model> bus_models;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        recyclerView = findViewById(R.id.recyclerView_servicelist);

        editsearch = findViewById(R.id.search);
      //  txtError = findViewById(R.id.txtError);
        editsearch.setIconifiedByDefault(false);
        editsearch.setQueryHint(hint);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        editsearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        editsearch.setOnQueryTextListener(this);

        bus_models = new ArrayList< Bus_Model>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent().getExtras() != null) {
            hint = getIntent().getStringExtra("hint");
            tag = getIntent().getStringExtra("tag");
        }

        getData();
        mRequestQueue = Volley.newRequestQueue(this);

    }

   private void getData() {
        progressDialog = ProgressDialog.show(BusList.this, "Loading Data", "Please Wait...", false, false);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        HttpsTrustManager.allowAllSSL();
        // Enter the correct url for your api service site
        String url = "https://202.143.96.44:1831/api/Mob/Get_Bus_CityList";//getResources().getString(R.string.url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {

                      //  Log.d("TAGTransaction", "GetTransactions:" + response);
                        for (int i = 0;i<response.length();i++){

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Bus_Model bus_model = new Bus_Model();

                                bus_model.setCityName(jsonObject.getString("CityName").toString());

                                bus_models.add(bus_model);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        busListAdapter = new BusListAdapter(getLayoutInflater(), bus_models);
                        recyclerView.setAdapter(busListAdapter);
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("ErrorTAG","Error"+error);

            }

        });
        requestQueue.add(jsonArrayRequest);
    }

    ///////////////


    public class BusListAdapter extends RecyclerView.Adapter<BusListAdapter.ViewHolder> {
        LayoutInflater layoutInflater;
        List< Bus_Model> Buslist;
        String searchString = "";
        private ArrayList<Bus_Model> arraylist;



        public BusListAdapter(LayoutInflater layoutInflater, List< Bus_Model> Buslist) {
            this.layoutInflater = layoutInflater;
            this.Buslist = Buslist;
            this.arraylist = new ArrayList<Bus_Model>();
            this.arraylist.addAll(Buslist);
        }

        @NonNull
        @Override
        public BusListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.busbooking_item, parent, false);
            return new BusListAdapter.ViewHolder(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Bus_Model bus_model = new Bus_Model();
            pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            //AirportModel.TransferAirport transferAirport = new AirportModel.TransferAirport();
            holder.cityname.setText(Buslist.get(position).getCityName());
            // holder.cityname.setText(airportLists.get(position).getCityName()+","+airportLists.get(position).getCountryName());
            //  holder.airportcode.setText(airportLists.get(position).getAiportCode());
            //  Log.v("AdapterTAG", "" + airportLists.get(position).getCountryName());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
             /*       editor1.putString("dest", holder.cityname.getText().toString());
                    editor1.putString("dest1",holder.airportName.getText().toString());

                    editor1.apply();
                    editor.putString("source", holder.cityname.getText().toString());
                    editor.putString("source1",holder.airportName.getText().toString());
                    editor.apply();*/
                    String value = holder.cityname.getText().toString().trim();
                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", value);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return Buslist.size();
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            Buslist.clear();
            if (charText.length() == 0) {
                Buslist.addAll(arraylist);
            } else {
                for (Bus_Model s : arraylist) {
                    if (s.getCityName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        Buslist.add(s);
                    }

                }
            }
            notifyDataSetChanged();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView cityname,airportName,airportcode;
            ImageView img_icon;
            private String tag;
            public ViewHolder(View view) {
                super(view);
              //  airportName = view.findViewById(R.id.airportname);
                cityname = view.findViewById(R.id.city_name);
             //   airportcode = view.findViewById(R.id.airportcode);
               // img_icon = view.findViewById(R.id.imv_transaction_type);

            }
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (busListAdapter != null) {
            busListAdapter.filter(newText);
        }
        return true;    }
}