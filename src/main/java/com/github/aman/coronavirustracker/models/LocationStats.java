package com.github.aman.coronavirustracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int latestCount;

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCount=" + latestCount +
                '}';
    }

    public LocationStats(String state, String country, int latestCount) {
        this.state = state;
        this.country = country;
        this.latestCount = latestCount;
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
}
