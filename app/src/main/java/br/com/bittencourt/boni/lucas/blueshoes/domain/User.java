package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class User {
    private String name;
    private int image;
    boolean status;

    public User(String name, int image, boolean status) {
        this.name = name;
        this.image = image;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public boolean isStatus() {
        return status;
    }
}
