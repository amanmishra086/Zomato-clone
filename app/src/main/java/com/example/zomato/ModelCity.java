package com.example.zomato;

public class ModelCity {

    String url,city,country;
    String id;

    public ModelCity(String url, String city, String country, String id) {
        this.url = url;
        this.city = city;
        this.country = country;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
