package com.example.bus_booking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_booking.Model.Bus_Model;

import java.util.ArrayList;
import java.util.Locale;

public class BusListAdapter  extends RecyclerView.Adapter<BusListAdapter.ExampleViewHolder> implements SearchView.OnQueryTextListener {
    private Context mContext;
    private ArrayList<Bus_Model> mExampleList;
    private ArrayList<Bus_Model> arraylist;
    BusListAdapter busListAdapter;

    public BusListAdapter(Context context, ArrayList<Bus_Model> exampleList) {
        mContext = context;
        mExampleList = exampleList;
        this.arraylist = new ArrayList<Bus_Model>();
        //this.arraylist.addAll(BusListAdapter);
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.busbooking_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Bus_Model currentItem = mExampleList.get(position);


        holder.mTextViewCreator.setText(currentItem.getCityName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (currentItem.getCityName().equals("Aaspura")) {
                    Intent i3 = new Intent(mContext, MainActivity.class);
                    i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i3);

                }
               /* else if(currentItem.getBillerID().equals("NPCL00000NOI01")){
                    Intent i2 = new Intent(mContext, Electricity2.class);
                    i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i2);

                }*/

                /*    switch (position) {


                        case 0:
                            Intent i3 = new Intent(mContext, Electricity2.class);
                            i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(i3);
                            break;

                        case 1:
                            Intent i2 = new Intent(mContext, Electricity2.class);
                            i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(i2);
                            break;
                    }*/

            }
        });

    }



    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            // mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.city_name);
            //  mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }


    // Filter Class
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mExampleList.clear();
        if (charText.length() == 0) {
            mExampleList.addAll(arraylist);
        } else {
            for (Bus_Model s : arraylist) {
                if (s.getCityName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mExampleList.add(s);
                }else if (s.getBusCityCode().toLowerCase().contains(charText)) {
                    mExampleList.add(s);
                }  else if (s.getCityName().toLowerCase().contains(charText)) {
                    mExampleList.add(s);
                } else if (s.getCityID().toLowerCase().contains(charText)) {
                    mExampleList.add(s);
                }/* else if (s.getCountryCode().toLowerCase().contains(charText)) {
                    airportLists.add(s);
                }*/
            }
        }
        notifyDataSetChanged();
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
        return true;
    }
}

/*
  @Override
        public int getItemCount() {
            return airportLists.size();
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            airportLists.clear();
            if (charText.length() == 0) {
                airportLists.addAll(arraylist);
            } else {
                for (AirportModel.TransferAirport s : arraylist) {
                    if (s.getAirportName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        airportLists.add(s);
                    }else if (s.getAiportCode().toLowerCase().contains(charText)) {
                        airportLists.add(s);
                    }  else if (s.getCityName().toLowerCase().contains(charText)) {
                        airportLists.add(s);
                    } else if (s.getCountryName().toLowerCase().contains(charText)) {
                        airportLists.add(s);
                    }*/
/* else if (s.getCountryCode().toLowerCase().contains(charText)) {
                    airportLists.add(s);
                }*//*

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
                airportName = view.findViewById(R.id.airportname);
                cityname = view.findViewById(R.id.city_name);
                airportcode = view.findViewById(R.id.airportcode);
                img_icon = view.findViewById(R.id.imv_transaction_type);

            }
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (airportListAdapter != null) {
            airportListAdapter.filter(newText);
        }
        return true;    }
}*/
