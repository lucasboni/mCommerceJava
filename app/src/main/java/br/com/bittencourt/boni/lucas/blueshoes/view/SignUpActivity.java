package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

public class SignUpActivity extends FormActivity implements KeyboardUtils.OnSoftInputChangedListener {


    private FrameLayout fl_form;
    private EditText et_email;
    private EditText et_password;
    private EditText et_confirm_password;
    private Button bt_sign_up;
    private TextView tv_privacy_policy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fl_form = findViewById(R.id.fl_form);

        /*
         * Colocando a View de um arquivo XML como View filha
         * do item indicado no terceiro argumento.
         * */
        View.inflate(this, R.layout.content_sign_up,fl_form);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        bt_sign_up = findViewById(R.id.bt_sign_up);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);


        /*
         * Com a API KeyboardUtils conseguimos de maneira
         * simples obter o status atual do teclado virtual (aberto /
         * fechado) e assim prosseguir com algoritmos de ajuste de
         * layout.
         * */
        KeyboardUtils.registerSoftInputChangedListener( this, this );


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
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.unregisterSoftInputChangedListener( this );
    }

    @Override
    void mainAction(View view) {
        blockFields( true );
        isMainButtonSending( true );
        showProxy( true );
        backEndFakeDelay(false, getString( R.string.invalid_sign_up_email ));
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
    public void onSoftInputChanged(int height) {
        changePrivacyPolicyConstraints(KeyboardUtils.isSoftInputVisible( this ));
    }


    private void changePrivacyPolicyConstraints(Boolean isKeyBoardOpened ){

        if(tv_privacy_policy == null) return;//verifica se esta nulo

        int privacyId = tv_privacy_policy.getId();
        ConstraintLayout parent = (ConstraintLayout)tv_privacy_policy.getParent();
        ConstraintSet constraintSet = new ConstraintSet();

        /*
         * Definindo a largura e a altura da View em
         * mudança de constraints, caso contrário ela
         * fica com largura e altura em 0dp.
         * */
        constraintSet.constrainWidth(
                privacyId,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        constraintSet.constrainHeight(
                privacyId,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        /*
         * Centralizando a View horizontalmente no
         * ConstraintLayout.
         * */
        constraintSet.centerHorizontally(
                privacyId,
                ConstraintLayout.LayoutParams.PARENT_ID
        );

        if( isKeyBoardOpened || ScreenUtils.isLandscape() ){
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
                    (int)(12 * ScreenUtils.getScreenDensity())
            );
        }
        else{
            /*
             * Se o teclado virtual estiver fechado, então
             * mude a configuração da View alvo
             * (tv_privacy_policy) para ficar vinculada ao
             * fundo do ConstraintLayout ancestral.
             * */
            constraintSet.connect(
                    privacyId,
                    ConstraintLayout.LayoutParams.BOTTOM,
                    ConstraintLayout.LayoutParams.PARENT_ID,
                    ConstraintLayout.LayoutParams.BOTTOM
            );
        }

        constraintSet.applyTo( parent );
    }


    /* Listeners de clique */
    void callPrivacyPolicyFragment(View view){
        Intent intent = new Intent(
                this,
                MainActivity.class);

        /*
         * Para saber qual fragmento abrir quando a
         * MainActivity voltar ao foreground.
         * */
        intent.putExtra(
                MainActivity.FRAGMENT_ID,
                R.id.item_privacy_policy
        );

        /*
         * Removendo da pilha de atividades a primeira
         * MainActivity aberta (e a LoginActivity), para
         * deixar somente a nova MainActivity com uma nova
         * configuração de fragmento aberto.
         * */
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity( intent );
    }

    void callLoginActivity(View view  ){
        finish();
    }
}
