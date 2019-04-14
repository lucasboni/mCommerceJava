package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private FrameLayout fl_proxy_container;
    private Button bt_login;
    private FrameLayout fl_form_container;
    private TextView tv_privacy_policy;
    private  TextView tv_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//ativa a setade voltar do actionbar

        /*
         * Hackcode para que a imagem de background do layout não
         * se ajuste de acordo com a abertura do teclado de
         * digitação. Caso utilizando o atributo
         * android:background, o ajuste ocorre, desconfigurando o
         * layout.
         * */
        getWindow().setBackgroundDrawableResource(R.drawable.bg_activity);

        et_email =findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        fl_proxy_container = findViewById(R.id.fl_proxy_container);
        bt_login= findViewById(R.id.bt_login);
        fl_form_container = findViewById(R.id.fl_form_container);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);
        tv_sign_up = findViewById(R.id.tv_sign_up);


        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                String message = getString(R.string.invalid_email);


                if( !content.toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(content).matches())//verifica se é um email valido
                    message = null;

                et_email.setError(message);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
                String message = getString(R.string.invalid_password);

                if( content.length() > 5 )//checa se esta muito pequeno
                    message = null;

                et_password.setError(message);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        et_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            /*
             * Caso o usuário toque no botão "Done" do teclado virtual
             * ao invés de tocar no botão "Entrar". Mesmo assim temos
             * de processar o formulário.
             * */
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {

                if( actionId == EditorInfo.IME_ACTION_DONE ){//verifica seapertou no botão done
                    closeVirtualKeyBoard( view );
                    login(view);
                    return true; /* Indica que o algoritmo do método consumiu o evento. */
                }

                return false;
            }
        });

        /*
         * Com a API KeyboardUtils conseguimos de maneira
         * simples obter o status atual do teclado virtual (aberto /
         * fechado) e assim prosseguir com algoritmos de ajuste de
         * layout.
         * */
        KeyboardUtils.registerSoftInputChangedListener(this, new KeyboardUtils.OnSoftInputChangedListener() {
            @Override
            public void onSoftInputChanged(int height) {
                if( ScreenUtils.isPortrait() ){
                    changePrivacyPolicyConstraints(
                            KeyboardUtils.isSoftInputVisible( LoginActivity.this )
                    );
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this);
        super.onDestroy();
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
        View snackBarView = snackBar.getView();
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);

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

    /*
     * Necessário para que os campos de formulário não possam
     * ser acionados depois de enviados os dados.
     * */
    private void blockFields( boolean status ){
        et_email.setEnabled(!status);
        et_password.setEnabled(!status);
        bt_login.setEnabled(!status);
    }

    /*
     * Muda o rótulo do botão de login de acordo com o status
     * do envio de dados de login.
     * */
    private void isSignInGoing( boolean status ){

        if(status){
            bt_login.setText( getString( R.string.sign_in_going ));/* Entrando... */
        }else{
            bt_login.setText( getString( R.string.sign_in ));/* Entrar */
        }
    }

    private void backEndFakeDelay(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                 * Simulando um delay de latência de
                 * 1 segundo.
                 * */
                SystemClock.sleep( 1000 );

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        blockFields( false );
                        isSignInGoing( false );
                        showProxy( false );
                        snackBarFeedback(fl_form_container,false,getString( R.string.invalid_login )
                        );
                    }
                });
            }
        }).start();

    }

    public void login(View view){
        blockFields( true );
        isSignInGoing( true );
        showProxy( true );
        backEndFakeDelay();
    }

    //esconde oteclado virtual
    private void closeVirtualKeyBoard( View view ){
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
    }



    private void changePrivacyPolicyConstraints(boolean isKeyBoardOpened){

        int privacyId = tv_privacy_policy.getId();
        ConstraintLayout parent = (ConstraintLayout)tv_privacy_policy.getParent();
        ConstraintSet constraintSet =  new ConstraintSet();

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

        if( isKeyBoardOpened ){
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

}
