package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomTypefaceSpan extends TypefaceSpan {

    private Typeface newTypeFace ;


    public CustomTypefaceSpan(Typeface newTypeFace) {
        super("");
        this.newTypeFace = newTypeFace;
    }



    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        applyCustomTypeFace(ds,newTypeFace);
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint paint) {
        super.updateMeasureState(paint);
        applyCustomTypeFace(paint,newTypeFace);
    }

    private void applyCustomTypeFace(
            Paint paint ,
            Typeface typeface
    ) {

        int styleAnterior;
        Typeface typefaceAnterior = paint.getTypeface();

        if( typefaceAnterior == null ){
            styleAnterior = 0;
        }
        else {
            styleAnterior = typefaceAnterior.getStyle();
        }

        /*
         * Para verificar a compatibilidade de estilos.
         * */
        boolean fake = (styleAnterior & typeface.getStyle()) !=0;

        /*
         * Verifica se a fonte mais atual já está de acordo
         * com a anterior em termos de "texto em negrito",
         * caso não, atualiza.
         * */
        if( fake && Typeface.BOLD != 0 ) {
            paint.setFakeBoldText( true );
        }

        /*
         * Verifica se a fonte mais atual já está de acordo
         * com a anterior em termos de "texto em itálico",
         * caso não, atualiza.
         * */
        if( fake && Typeface.ITALIC != 0 ){
            paint.setTextSkewX( -0.25f );
        }

        /*
         * Aplica a fonte.
         * */
        paint.setTypeface( typeface );
    };
}
