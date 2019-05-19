package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

public class SignUpActivity extends FormEmailAndPasswordActivity {


    private EditText et_email;
    private EditText et_password;
    private EditText et_confirm_password;
    private Button bt_sign_up;
    private TextView tv_privacy_policy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        bt_sign_up = findViewById(R.id.bt_sign_up);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);



        /*
         * Colocando configuração de validação de campo de email
         * para enquanto o usuário informa o conteúdo deste campo.
         * */
        extension_functions.isValidPassword(et_email,getString(R.string.invalid_email));

        /*
         * Colocando configuração de validação de campo de senha
         * para enquanto o usuário informa o conteúdo deste campo.
         * */
        extension_functions.isValidPassword(et_password,getString(R.string.invalid_password));

        /*
         * Colocando configuração de validação de campo de
         * confirmação de senha para enquanto o usuário informa o
         * conteúdo deste campo.
         * */
        et_confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                /*
                 * O toString() em et_password.text.toString() é
                 * necessário, caso contrário a validação falha
                 * mesmo quando é para ser ok.
                 * */
                if(!this.equals( et_password.getText().toString() )){
                    et_confirm_password.setError(getString(R.string.invalid_confirmed_password ));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


    @Override
    protected int getLayoutResourceID() {
        return R.layout.content_sign_up;
    }

    @Override
    void backEndFakeDelay() {
        backEndFakeDelay(false, getString(R.string.invalid_sign_up_email));
    }

    @Override
    void blockFields(Boolean status) {
        et_email.setEnabled(!status);
        et_password.setEnabled(!status);
        et_confirm_password.setEnabled(!status);
        bt_sign_up.setEnabled(!status);
    }

    @Override
    void isMainButtonSending(Boolean status) {
        if( status )
            bt_sign_up.setText(getString( R.string.sign_up_going ));
        else
            bt_sign_up.setText(getString( R.string.sign_up ));
    }

    @Override
    boolean isConstraintToSiblingView(boolean isKeyBoardOpened) {
        return isKeyBoardOpened || ScreenUtils.isLandscape();
    }

    @Override
    void setConstraintsRelativeToSiblingView(ConstraintSet constraintSet, int privacyId) {
        /*
         * Se o teclado virtual estiver aberto, então
         * mude a configuração da View alvo
         * (tv_privacy_policy) para ficar vinculada a
         * View acima dela (tv_sign_up).
         * */
        constraintSet.connect(
                privacyId,
                ConstraintLayout.LayoutParams.TOP,
                bt_sign_up.getId(),
                ConstraintLayout.LayoutParams.BOTTOM,
                (int) (12 * ScreenUtils.getScreenDensity()));
    }


    void callLoginActivity(View view  ){
        finish();
    }
}
