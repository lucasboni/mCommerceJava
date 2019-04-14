package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public abstract class FormActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FrameLayout fl_proxy_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login );


        toolbar =findViewById(R.id.toolbar);
        fl_proxy_container =findViewById(R.id.fl_proxy_container);

        setSupportActionBar( toolbar );

         getSupportActionBar().setDisplayHomeAsUpEnabled( true );

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
    private void showProxy(boolean status){
        if(status){
            fl_proxy_container.setVisibility(View.VISIBLE);
        }else{
            fl_proxy_container.setVisibility(View.GONE);
        }
    }


}
