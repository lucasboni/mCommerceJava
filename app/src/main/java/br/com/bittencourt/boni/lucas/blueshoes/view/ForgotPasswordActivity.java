package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

public class ForgotPasswordActivity extends FormActivity {

    private EditText et_email;
    private FrameLayout fl_proxy_container;
    private Button bt_recover_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        et_email =findViewById(R.id.et_email);
        fl_proxy_container = findViewById(R.id.fl_proxy_container);
        bt_recover_password= findViewById(R.id.bt_recover_password);


        extension_functions.isValidEmail(et_email,getString(R.string.invalid_email));

    }


    @Override
    protected int getLayoutResourceID() {
        return R.layout.content_forgot_password;
    }


    @Override
    void backEndFakeDelay() {
        backEndFakeDelay(false,getString( R.string.invalid_login ));
    }

    @Override
    public void blockFields(Boolean status) {
        et_email.setEnabled(!status);
        bt_recover_password.setEnabled(!status);
    }

    @Override
    protected void isMainButtonSending(Boolean status) {/* Antigo isSignInGoing() */
        if(status){
            bt_recover_password.setText( getString( R.string.sign_in_going ));/* Entrando... */
        }else{
            bt_recover_password.setText( getString( R.string.sign_in ));/* Entrar */
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
}
