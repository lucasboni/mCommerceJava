package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.content.Context;
import android.os.IInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class extension_functions {

    /*
     * A seguir códigos de validação de CPF e CNPJ.
     *
     * Fontes:
     *      Código: https://www.vivaolinux.com.br/script/Codigo-para-validar-CPF-e-CNPJ-otimizado
     *      Explicação: https://www.geradorcpf.com/algoritmo_do_cpf.htm
     * */

    private static int[] weightCPF = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static int[] weightCNPJ = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static void isGernicValidation(EditText editText, Validater validater, String msg) {
        editText.addTextChangedListener(isValid(editText, validater, msg));
    }

    public static void isValidEmail(EditText editText, String msg) {
        editText.addTextChangedListener(isValid(editText, _isValidEmail(), msg));
    }

    public static void isValidPassword(EditText editText, String msg) {
        editText.addTextChangedListener(isValid(editText, _isValidPassword(), msg));
    }

    private static Validater _isValidEmail() {
        return new Validater() {
            @Override
            public boolean validar(CharSequence content) {
                return (!content.toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(content).matches());//verifica se é um email valido;
            }
        };
    }

    private static Validater _isValidPassword() {

        return new Validater() {
            @Override
            public boolean validar(CharSequence content) {
                return content.length() > 5;//verifica se é maior que 5 o numero de caracteres
            }
        };
    }

    private static TextWatcher isValid(final EditText editText, final Validater validater, final String msg) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                if (!validater.validar(content)) {
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


    static public boolean isValidCPF(String value) {
        if( value.length() != 11 )
            return false;

        Integer digit1 = digitCalc( value.substring(0, 9), weightCPF );
        Integer digit2 = digitCalc(value.substring(0, 9) + digit1, weightCPF );

        return value.equalsIgnoreCase(value.substring(0, 9) + digit1.toString() + digit2.toString());
    }

    static public boolean isValidCNPJ(String value) {
        if( value.length() != 14 )
            return false;

        Integer digit1 = digitCalc( value.substring(0, 12), weightCNPJ );
        Integer digit2 = digitCalc( value.substring(0, 12) + digit1, weightCNPJ );

        return value.equalsIgnoreCase(value.substring(0, 12) + digit1.toString() + digit2.toString());
    }

    private static Integer digitCalc(
            String str,
            int[] weight) {

        int sum = 0;
        int index = str.length() - 1;
        int digit;

        while (index >= 0) {
            digit = Integer.parseInt(str.substring(index, index + 1));
            sum += digit * weight[weight.length - str.length() + index];
            index--;
        }

        sum = 11 - sum % 11;

        if (sum > 9)
            return 0;
        else
            return sum;
    }



    public interface Validater {
        boolean validar(CharSequence content);
    }


}
