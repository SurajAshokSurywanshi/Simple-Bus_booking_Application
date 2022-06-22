package com.example.bus_booking.Model;

import java.io.IOException;

public class Bus_Model {

    private String cityID;
    private String cityName;
    private String busCityCode;
    private CountryCode countryCode;



    public Bus_Model(String cityName) {
        this.cityName = cityName;

    }

    public Bus_Model() {

    }

    public String getCityID() { return cityID; }
    public void setCityID(String value) { this.cityID = value; }

    public String getCityName() { return cityName; }
    public void setCityName(String value) { this.cityName = value; }

    public String getBusCityCode() { return busCityCode; }
    public void setBusCityCode(String value) { this.busCityCode = value; }

    public CountryCode getCountryCode() { return countryCode; }
    public void setCountryCode(CountryCode value) { this.countryCode = value; }

    public enum CountryCode {
        EMPTY, IN;

        public String toValue() {
            switch (this) {
                case EMPTY: return "";
                case IN: return "IN";
            }
            return null;
        }

        public static CountryCode forValue(String value) throws IOException {
            if (value.equals("")) return EMPTY;
            if (value.equals("IN")) return IN;
            throw new IOException("Cannot deserialize CountryCode");
        }

    }




}

