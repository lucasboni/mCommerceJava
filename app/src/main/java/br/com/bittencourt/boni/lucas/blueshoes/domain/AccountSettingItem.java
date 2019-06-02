package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class AccountSettingItem {
    private String label;
    private String description;


    public AccountSettingItem(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
