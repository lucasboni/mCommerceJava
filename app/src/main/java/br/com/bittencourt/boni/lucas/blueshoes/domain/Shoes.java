package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class Shoes {
    String model;
    String mainImg;
    Brand brand;
    Price price;
    Rate rate;


    public Shoes(String model, String mainImg, Brand brand, Price price, Rate rate) {
        this.model = model;
        this.mainImg = mainImg;
        this.brand = brand;
        this.price = price;
        this.rate = rate;
    }


    public String getModel() {
        return model;
    }

    public String getMainImg() {
        return mainImg;
    }

    public Brand getBrand() {
        return brand;
    }

    public Price getPrice() {
        return price;
    }

    public Rate getRate() {
        return rate;
    }
}
