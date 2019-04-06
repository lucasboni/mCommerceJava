package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class NavMenuItem {
    public static final int DEFAULT_ICON_ID  = -1;//padrao null object

    private long id;//chave estavel, que nao pode mudardo item
    private String label;
    private int icon  = DEFAULT_ICON_ID;

    public NavMenuItem(long id,String label) {
        this.id = id;
        this.label = label;
    }

    public NavMenuItem(long id,String label, int icon) {
        this.id = id;
        this.label = label;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getIcon() {
        return icon;
    }
}
