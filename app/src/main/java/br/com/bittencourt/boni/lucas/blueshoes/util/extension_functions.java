package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.content.Context;
import android.os.IInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class extension_functions {

    public static void isGernicValidation(EditText editText,Validater validater,String msg){
        editText.addTextChangedListener(isValid(editText,validater,msg));
    }

    public static void isValidEmail(EditText editText,String msg){
        editText.addTextChangedListener(isValid(editText,_isValidEmail(),msg));
    }

    public static void isValidPassword(EditText editText,String msg){
        editText.addTextChangedListener(isValid(editText,_isValidPassword(),msg));
    }

    private static Validater _isValidEmail(){
        return new Validater() {
            @Override
            public boolean validar(CharSequence content) {
                return (!content.toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(content).matches());//verifica se é um email valido;
            }
        };
    }

    private static Validater _isValidPassword(){

        return  new Validater() {
            @Override
            public boolean validar(CharSequence content) {
                return content.length() > 5;//verifica se é maior que 5 o numero de caracteres
            }
        };
    }

    private static TextWatcher isValid(final EditText editText,final Validater validater,final String msg){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                if(!validater.validar(content)){
                    editText.setError(msg);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }


    public interface Validater{
        boolean validar(CharSequence content);
    }


}
