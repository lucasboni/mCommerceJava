package br.com.bittencourt.boni.lucas.blueshoes.domain;

import br.com.bittencourt.boni.lucas.blueshoes.view.FormActivity;

public class AccountSettingItem {
    private String label;
    private String description;
    Class activityClass;


    /*public AccountSettingItem(String label, String description) {
        this.label = label;
        this.description = description;
    }*/

    public AccountSettingItem(String label, String description, Class activityClass) {
        this.label = label;
        this.description = description;
        this.activityClass = activityClass;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Class getActivityClass() {
        return activityClass;
    }
}
