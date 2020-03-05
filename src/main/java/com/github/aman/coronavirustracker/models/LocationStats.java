package com.github.aman.coronavirustracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int latestCount;
    private int dayOverDayChange;

    public LocationStats(String state, String country, int latestCount, int dayOverDayChange) {
        this.state = state;
        this.country = country;
        this.latestCount = latestCount;
        this.dayOverDayChange = dayOverDayChange;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCount=" + latestCount +
                ", dayOverDayChange=" + dayOverDayChange +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestCount() {
        return latestCount;
    }

    public void setLatestCount(int latestCount) {
        this.latestCount = latestCount;
    }

    public int getDayOverDayChange() {
        return dayOverDayChange;
    }

    public void setDayOverDayChange(int dayOverDayChange) {
        this.dayOverDayChange = dayOverDayChange;
    }

}
