package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

public class ForgotPasswordActivity extends FormActivity {

    private EditText et_email;
    private FrameLayout fl_proxy_container;
    private Button bt_recover_password;


    private FrameLayout fl_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fl_form= findViewById(R.id.fl_form);

        /*
         * Colocando a View de um arquivo XML como View filha
         * do item indicado no terceiro argumento.
         * */
        View.inflate(this,R.layout.content_forgot_password,fl_form);

        et_email =findViewById(R.id.et_email);
        fl_proxy_container = findViewById(R.id.fl_proxy_container);
        bt_recover_password= findViewById(R.id.bt_recover_password);


        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                if(!extension_functions.isValidEmail(content)){
                    et_email.setError(getString(R.string.invalid_email));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bt_recover_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAction(v);
            }
        });


    }

    @Override
    void mainAction(View view) { /* Antigo login() */
        blockFields( true );
        isMainButtonSending( true );
        showProxy( true );
        backEndFakeDelay(false,getString( R.string.invalid_login ));
    }

    @Override
    void blockFields(Boolean status) {
        et_email.setEnabled(!status);
        bt_recover_password.setEnabled(!status);
    }

    @Override
    void isMainButtonSending(Boolean status) {/* Antigo isSignInGoing() */
        if(status){
            bt_recover_password.setText( getString( R.string.sign_in_going ));/* Entrando... */
        }else{
            bt_recover_password.setText( getString( R.string.sign_in ));/* Entrar */
        }
    }

    /*
     * Apresenta a tela de bloqueio que diz ao usuário que
     * algo está sendo processado em background e que ele
     * deve aguardar.
     * */
    private void showProxy(boolean status){
        if(status){
            fl_proxy_container.setVisibility(View.VISIBLE);
        }else{
            fl_proxy_container.setVisibility(View.GONE);
        }
    }

    /*
     * Método responsável por apresentar um SnackBar com as
     * corretas configurações de acordo com o feedback do
     * back-end Web.
     * */
    private void snackBarFeedback(ViewGroup viewContainer,boolean status,String message){
        Snackbar snackBar = Snackbar.make(viewContainer,message,Snackbar.LENGTH_LONG);

        /*
         * Acessando o TextView padrão do SnackBar para assim
         * colocarmos um ícone nele via objeto Spannable.
         * */
        TextView textView = snackBar.getView().findViewById(android.support.design.R.id.snackbar_text);

        /*
         * Criando o objeto Drawable que entrará como ícone
         * inicial no texto do SnackBar.
         * */

        int iconResource;
        if(status){
            iconResource = R.drawable.ic_check_black_18dp;
        }else{
            iconResource = R.drawable.ic_close_black_18dp;
        }

        Drawable img = ResourcesCompat.getDrawable(getResources(),iconResource,null);
        img.setBounds(0,0,img.getIntrinsicWidth(),img.getIntrinsicHeight());


        int iconColor;

        if(status){
            iconColor = ContextCompat.getColor(this,R.color.colorNavButton);
        }else{
            iconColor= Color.RED;
        }

        img.setColorFilter( iconColor, PorterDuff.Mode.SRC_ATOP);

        SpannableString spannedText = new SpannableString( textView.getText() );
        spannedText.setSpan(
                new ImageSpan( img, ImageSpan.ALIGN_BOTTOM ),
                0,
                1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        textView.setText(spannedText,TextView.BufferType.SPANNABLE );
        snackBar.show();
    }
}
