package br.com.bittencourt.boni.lucas.blueshoes.domain;

import android.content.Context;

import java.util.Locale;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class Price {
    private float normal;/* Preço normal. */
    private int parcels;
    private boolean hasDiscount;
    private float withDiscount;/* Preço com o desconto já aplicado. */

    public Price(float normal, int parcels, boolean hasDiscount, float withDiscount) {
        this.normal = normal;
        this.parcels = parcels;
        this.hasDiscount = hasDiscount;
        this.withDiscount = withDiscount;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }


    /*
     * Locale.GERMAN está sendo utilizado para que na
     * separação das casas decimais seja utilizada a
     * vírgula ao invés de ponto.
     * */

    public String getNormalLabel(Context context) {
        return String.format(
                Locale.GERMAN,
                "%s %.2f",
                context.getString(R.string.money_sign),
                normal
        );
    }

    public String getWithDiscountLabel(Context context){
        return String.format(
    Locale.GERMAN,
            "%s %.2f",
            context.getString( R.string.money_sign ),
    withDiscount
        );
    }

    public String getPercentDiscountLabel()   {
        float percent = ((normal - withDiscount) / normal) * 100;

        /*
         * Para apresentar o caractere de percentagem, %,
         * como parte do texto é preciso utilizar dois dele
         * como no String.format() abaixo.
         * */
        return String.format(
                "-%d%%",
                (int)percent
        );
    }

    public String getParcelsLabel(Context context) {
        float priceParcel;
        if( hasDiscount )
            priceParcel = withDiscount / parcels;
        else
            priceParcel = normal / parcels;

        return String.format(
                Locale.GERMAN,
                "%s %dx %s %s %.2f",
                context.getString( R.string.in_until ),
                parcels,
                context.getString( R.string.of ),
                context.getString( R.string.money_sign ),
                priceParcel
        );
    }
}
