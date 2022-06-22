package com.example.bus_booking.Model;

import java.io.IOException;
import java.util.UUID;

public class Bus_Search_Model {
    private BusSearchResult[] busSearchResult;
    private String sourceName;
    private String destinationName;
    private String dateOfJourney;
    private UUID sessionID;
    private Currency.Status status;


    public BusSearchResult[] getBusSearchResult() { return busSearchResult; }
    public void setBusSearchResult(BusSearchResult[] value) { this.busSearchResult = value; }

    public String getSourceName() { return sourceName; }
    public void setSourceName(String value) { this.sourceName = value; }

    public String getDestinationName() { return destinationName; }
    public void setDestinationName(String value) { this.destinationName = value; }

    public String getDateOfJourney() { return dateOfJourney; }
    public void setDateOfJourney(String value) { this.dateOfJourney = value; }

    public UUID getSessionID() { return sessionID; }
    public void setSessionID(UUID value) { this.sessionID = value; }

    public Currency.Status getStatus() { return status; }
    public void setStatus(Currency.Status value) { this.status = value; }
    public static class BusSearchResult {
        private String routeID;
        private String busType;
        private String serviceName;
        private String travelName;
        private Currency currency;
        private String departureTime;
        private String arrivalTime;
        private long busSource;
        private long availableSeats;
        private String availableFares;
        private CancelPolicy[] cancelPolicy;
        private IngPointsDetail[] boardingPointsDetails;
        private IngPointsDetail[] droppingPointsDetails;
        private String  price;

        public BusSearchResult(String busType) {
            this.busType = busType;

        }


        public String getRouteID() { return routeID; }
        public void setRouteID(String value) { this.routeID = value; }

        public String getBusType() { return busType; }
        public void setBusType(String value) { this.busType = value; }

        public String getServiceName() { return serviceName; }
        public void setServiceName(String value) { this.serviceName = value; }

        public String getTravelName() { return travelName; }
        public void setTravelName(String value) { this.travelName = value; }

        public Currency getCurrency() { return currency; }
        public void setCurrency(Currency value) { this.currency = value; }

        public String getDepartureTime() { return departureTime; }
        public void setDepartureTime(String value) { this.departureTime = value; }

        public String getArrivalTime() { return arrivalTime; }
        public void setArrivalTime(String value) { this.arrivalTime = value; }

        public long getBusSource() { return busSource; }
        public void setBusSource(long value) { this.busSource = value; }

        public long getAvailableSeats() { return availableSeats; }
        public void setAvailableSeats(long value) { this.availableSeats = value; }

        public String getAvailableFares() { return availableFares; }
        public void setAvailableFares(String value) { this.availableFares = value; }

        public CancelPolicy[] getCancelPolicy() { return cancelPolicy; }
        public void setCancelPolicy(CancelPolicy[] value) { this.cancelPolicy = value; }

        public IngPointsDetail[] getBoardingPointsDetails() { return boardingPointsDetails; }
        public void setBoardingPointsDetails(IngPointsDetail[] value) { this.boardingPointsDetails = value; }

        public IngPointsDetail[] getDroppingPointsDetails() { return droppingPointsDetails; }
        public void setDroppingPointsDetails(IngPointsDetail[] value) { this.droppingPointsDetails = value; }

        public String getPrice() { return price; }
        public void setPrice(String  value) { this.price = value; }
    }

}




class IngPointsDetail {
    private long cityPointID;
    private long busID;
    private String cityPointName;
    private String cityPointLocation;
    private Object cityPointLandmark;
    private Object cityPointAddress;
    private Object cityPointContactNumber;
    private String cityPointTime;

    public long getCityPointID() { return cityPointID; }
    public void setCityPointID(long value) { this.cityPointID = value; }

    public long getBusID() { return busID; }
    public void setBusID(long value) { this.busID = value; }

    public String getCityPointName() { return cityPointName; }
    public void setCityPointName(String value) { this.cityPointName = value; }

    public String getCityPointLocation() { return cityPointLocation; }
    public void setCityPointLocation(String value) { this.cityPointLocation = value; }

    public Object getCityPointLandmark() { return cityPointLandmark; }
    public void setCityPointLandmark(Object value) { this.cityPointLandmark = value; }

    public Object getCityPointAddress() { return cityPointAddress; }
    public void setCityPointAddress(Object value) { this.cityPointAddress = value; }

    public Object getCityPointContactNumber() { return cityPointContactNumber; }
    public void setCityPointContactNumber(Object value) { this.cityPointContactNumber = value; }

    public String getCityPointTime() { return cityPointTime; }
    public void setCityPointTime(String value) { this.cityPointTime = value; }
}


class CancelPolicy {
    private long busID;
    private String timeBeforeDept;
    private long cancellationChargeType;
    private long cancellationCharge;

    public long getBusID() { return busID; }
    public void setBusID(long value) { this.busID = value; }

    public String getTimeBeforeDept() { return timeBeforeDept; }
    public void setTimeBeforeDept(String value) { this.timeBeforeDept = value; }

    public long getCancellationChargeType() { return cancellationChargeType; }
    public void setCancellationChargeType(long value) { this.cancellationChargeType = value; }

    public long getCancellationCharge() { return cancellationCharge; }
    public void setCancellationCharge(long value) { this.cancellationCharge = value; }
}

// Currency.java



enum Currency {
    INR;

    public String toValue() {
        switch (this) {
            case INR: return "INR";
        }
        return null;
    }

    public static Currency forValue(String value) throws IOException {
        if (value.equals("INR")) return INR;
        throw new IOException("Cannot deserialize Currency");
    }


    public class Price {
        private long tdsCommission;
        private long priceID;
        private double publishedFare;
        private long agentCommission;
        private double seviceTax;
        private long tax;
        private String currency;
        private double rateOfExchange;
        private Currency currencyCode;
        private long discount;
        private long tdsRate;

        public long getTdsCommission() { return tdsCommission; }
        public void setTdsCommission(long value) { this.tdsCommission = value; }

        public long getPriceID() { return priceID; }
        public void setPriceID(long value) { this.priceID = value; }

        public double getPublishedFare() { return publishedFare; }
        public void setPublishedFare(double value) { this.publishedFare = value; }

        public long getAgentCommission() { return agentCommission; }
        public void setAgentCommission(long value) { this.agentCommission = value; }

        public double getSeviceTax() { return seviceTax; }
        public void setSeviceTax(double value) { this.seviceTax = value; }

        public long getTax() { return tax; }
        public void setTax(long value) { this.tax = value; }

        public String getCurrency() { return currency; }
        public void setCurrency(String value) { this.currency = value; }

        public double getRateOfExchange() { return rateOfExchange; }
        public void setRateOfExchange(double value) { this.rateOfExchange = value; }

        public Currency getCurrencyCode() { return currencyCode; }
        public void setCurrencyCode(Currency value) { this.currencyCode = value; }

        public long getDiscount() { return discount; }
        public void setDiscount(long value) { this.discount = value; }

        public long getTdsRate() { return tdsRate; }
        public void setTdsRate(long value) { this.tdsRate = value; }
    }

// ServiceName.java


    public enum ServiceName {
        SEAT_SELLER;

        public String toValue() {
            switch (this) {
                case SEAT_SELLER: return "Seat Seller";
            }
            return null;
        }

        public static ServiceName forValue(String value) throws IOException {
            if (value.equals("Seat Seller")) return SEAT_SELLER;
            throw new IOException("Cannot deserialize ServiceName");
        }
    }

// TravelName.java


    public enum TravelName {
        REDBUS_TRAVELS_TEST, TESTING_ACCOUNT, TEST_TPR;

        public String toValue() {
            switch (this) {
                case REDBUS_TRAVELS_TEST: return "Redbus Travels(Test)";
                case TESTING_ACCOUNT: return "TESTING ACCOUNT";
                case TEST_TPR: return "test_tpr";
            }
            return null;
        }

        public static TravelName forValue(String value) throws IOException {
            if (value.equals("Redbus Travels(Test)")) return REDBUS_TRAVELS_TEST;
            if (value.equals("TESTING ACCOUNT")) return TESTING_ACCOUNT;
            if (value.equals("test_tpr")) return TEST_TPR;
            throw new IOException("Cannot deserialize TravelName");
        }
    }

// Status.java


    public class Status {
        private String statusCode;
        private String description;
        private String category;

        public String getStatusCode() { return statusCode; }
        public void setStatusCode(String value) { this.statusCode = value; }

        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }

        public String getCategory() { return category; }
        public void setCategory(String value) { this.category = value; }
    }
}

// Price.java


