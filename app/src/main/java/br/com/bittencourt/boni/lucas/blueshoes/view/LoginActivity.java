package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

public class LoginActivity extends FormEmailAndPasswordActivity {

    private EditText et_email;
    private EditText et_password;
    private FrameLayout fl_proxy_container;
    private Button bt_login;
    private FrameLayout fl_form_container;
    private TextView tv_privacy_policy;
    private TextView tv_sign_up;
    private TextView tv_forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        et_email =findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        fl_proxy_container = findViewById(R.id.fl_proxy_container);
        bt_login= findViewById(R.id.bt_login);
        fl_form_container = findViewById(R.id.fl_form_container);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);



        extension_functions.isValidEmail(et_email,getString(R.string.invalid_email));

        extension_functions.isValidPassword(et_password,getString(R.string.invalid_password));

        et_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            /*
             * Caso o usuário toque no botão "Done" do teclado virtual
             * ao invés de tocar no botão "Entrar". Mesmo assim temos
             * de processar o formulário.
             * */
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {

                //if( actionId == EditorInfo.IME_ACTION_DONE ){//verifica seapertou no botão done(removido pois so umbotao tem vinculo com ese evento)
                    //closeVirtualKeyBoard( view );
                    mainAction(view);

                    //return true; /* Indica que o algoritmo do método consumiu o evento. */
                //}
                    /*
                    O return false indica à API interna que o listener de toque em algum botão de
                    action no teclado virtual não foi consumido e que o processamento interno deve
                    prosseguir. Porém, segundo testes, o processamento interno é apenas o fechamento
                    do teclado virtua
                    */
                return false;
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callForgotPasswordActivity(v);
            }
        });

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSignUpActivity(v);
            }
        });

    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.content_login;
    }


    @Override
    void backEndFakeDelay() {
        backEndFakeDelay(false,getString( R.string.invalid_login ));
    }

    @Override
    void blockFields(Boolean status) {
        et_email.setEnabled(!status);
        et_password.setEnabled(!status);
        bt_login.setEnabled(!status);
    }

    @Override
    void isMainButtonSending(Boolean status) {/* Antigo isSignInGoing() */

        if(status){
            bt_login.setText( getString( R.string.sign_in_going ));/* Entrando... */
        }else{
            bt_login.setText( getString( R.string.sign_in ));/* Entrar */
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
        TextView textView = snackBar.getView().findViewById(R.id.snackbar_text);

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

    @Override
    boolean isConstraintToSiblingView(boolean isKeyBoardOpened) {
        return isKeyBoardOpened;
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
                tv_sign_up.getId(),
                ConstraintLayout.LayoutParams.BOTTOM,
                (int) (12 * ScreenUtils.getScreenDensity())
        );
    }


    private void callForgotPasswordActivity( View view){
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void callSignUpActivity(View view) {
        /*
         * Para evitar que tenhamos mais de uma
         * SignUpActivity na pilha de atividades.
         * */
        if (ActivityUtils.isActivityExistsInStack(SignUpActivity.class)) {
            finish();
        } else {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected boolean isAbleToCallChangePrivacyPolicyConstraints() {
        return ScreenUtils.isPortrait();
    }

}
