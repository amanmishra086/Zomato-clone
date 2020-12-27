package com.example.zomato;

public class ModelRestaurant {
   int image;
    String name,address;
    String url;

    public ModelRestaurant(int image,String name, String address,String url) {
        this.name = name;
        this.address = address;
        this.image=image;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
