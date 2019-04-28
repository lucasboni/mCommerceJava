package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public abstract class FormActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FrameLayout fl_proxy_container;
    private FrameLayout fl_form_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_form);


        toolbar =findViewById(R.id.toolbar);
        fl_proxy_container =findViewById(R.id.fl_proxy_container);
        fl_form_container =findViewById(R.id.fl_form_container);

        setSupportActionBar( toolbar );

        /*
         * Para liberar o back button na barra de topo da
         * atividade.
         * */
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
         * Hackcode para que a imagem de background do layout não
         * se ajuste de acordo com a abertura do teclado de
         * digitação. Caso utilizando o atributo
         * android:background, o ajuste ocorre, desconfigurando o
         * layout.
         * */
        getWindow().setBackgroundDrawableResource( R.drawable.bg_activity );
    }


    /*
     * Apresenta a tela de bloqueio que diz ao usuário que
     * algo está sendo processado em background e que ele
     * deve aguardar.
     * */
    protected void showProxy(boolean status){
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
    private void snackBarFeedback(ViewGroup viewContainer, boolean status, String message){

        Snackbar snackBar = Snackbar
                .make(
                        viewContainer,
                        message,
                        Snackbar.LENGTH_LONG
                );
        View snackBarView = snackBar.getView();
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


        /*
         * Acessando o TextView padrão do SnackBar para assim
         * colocarmos um ícone nele via objeto Spannable.
         * */
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        SpannableString spannedText = new SpannableString( textView.getText() );

        /*
         * Acessando o TextView padrão do SnackBar para assim
         * colocarmos um ícone nele via objeto Spannable.
         * */

        /*
         * O espaçamento aplicado como parte do argumento
         * de SpannableString() é para que haja um espaço
         * entre o ícone e o texto do SnackBar, como
         * informado em protótipo estático.
         * */
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
     * Responsável por conter o algoritmo de envio / validação
     * de dados. Algoritmo vinculado ao menos ao principal
     * botão em tela.
     * */
    abstract void mainAction(View view);

    /*
     * Necessário para que os campos de formulário não possam
     * ser acionados depois de enviados os dados.
     * */
    abstract void blockFields(Boolean status  );

    /*
     * Muda o rótulo do botão principal de acordo com o status
     * do envio de dados.
     * */
    abstract void isMainButtonSending( Boolean status );


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == android.R.id.home ){
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item);
    }

    /*
     * Fake method - Somente para testes temporários em atividades
     * e fragmentos que contêm formulários.
     * */
    protected void backEndFakeDelay(final Boolean statusAction,final String feedbackMessage){

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
                        isMainButtonSending( false );
                        showProxy( false );
                        snackBarFeedback(fl_form_container,statusAction,feedbackMessage);
                    }
                });
            }
        }).start();

    }


}
