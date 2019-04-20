package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

public class extension_functions {

    public static boolean isValidEmail(CharSequence content){
        return (!content.toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(content).matches());//verifica se é um email valido
    }

    public static boolean isValidPassword(CharSequence content){
        return content.length() > 5;
    }



}
