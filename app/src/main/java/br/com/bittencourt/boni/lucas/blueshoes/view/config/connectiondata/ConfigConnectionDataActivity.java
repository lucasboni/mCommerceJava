package br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class ConfigConnectionDataActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_config_connection_data );
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar( toolbar );



        /*
         * Para liberar o back button na barra de topo da
         * atividade.
         * */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
         * Hackcode para que a imagem de background do layout não
         * se ajuste de acordo com a abertura do teclado de
         * digitação. Caso utilizando o atributo
         * android:background, o ajuste ocorre, desconfigurando o
         * layout.
         * */
        getWindow().setBackgroundDrawableResource( R.drawable.bg_activity );

        /*
         * Criando o adaptador de fragmentos que ficarão expostos
         * no ViewPager.
         * */
        ConfigConnectionDataSectionsAdapter sectionsPagerAdapter =
                new ConfigConnectionDataSectionsAdapter(
                        getSupportFragmentManager()
                );

        /*
         * Acessando o ViewPager e vinculando o adaptador de
         * fragmentos a ele.
         * */
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        /*
         * Acessando o TabLayout e vinculando ele ao ViewPager
         * para que haja sincronia na escolha realizada em
         * qualquer um destes componentes visuais.
         * */
        TabLayout tabs = findViewById( R.id.tabs );
        tabs.setupWithViewPager(viewPager);
    }


    /*
     * Para permitir que o back button tenha a ação de volta para
     * a atividade anterior.
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( item.getItemId() == android.R.id.home ){
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }



}
