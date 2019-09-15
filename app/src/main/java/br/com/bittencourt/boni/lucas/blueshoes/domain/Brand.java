package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class Brand {
    private String label;
    private String logo;

    public Brand(String label, String logo) {
        this.label = label;
        this.logo = logo;
    }


    public String getLabel() {
        return label;
    }

    public String getLogo() {
        return logo;
    }
}
