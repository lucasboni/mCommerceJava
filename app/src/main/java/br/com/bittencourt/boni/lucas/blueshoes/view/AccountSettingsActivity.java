package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.data.AccountSettingsItemsDataBase;
import br.com.bittencourt.boni.lucas.blueshoes.domain.User;

public class AccountSettingsActivity extends AppCompatActivity {


    private RecyclerView rv_account_settings_items;
    private TextView tv_user_connected;


    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_account_settings_items = findViewById(R.id.rv_account_settings_items);
        tv_user_connected = findViewById(R.id.tv_user_connected);

        /*
         * Colocando em tela o usuário conectado.
         * */

        user = getIntent().getParcelableExtra(User.KEY);
        tv_user_connected.setText(String.format(
                "%s %s",
                getString(R.string.connected),
                user.getName())
        );


        initItems();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == android.R.id.home ){
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }


    /*
     * Método que inicializa a lista de itens de configurações
     * de conta.
     * */
    private void initItems(){


        rv_account_settings_items.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        rv_account_settings_items.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                layoutManager.getOrientation()
        );
        divider.setDrawable(
                ContextCompat.getDrawable(
                        this,
                        R.drawable.light_grey_divider_line
                )
        );
        rv_account_settings_items.addItemDecoration( divider );

        rv_account_settings_items.setAdapter(new AccountSettingsItemsAdapter(
                AccountSettingsItemsDataBase.getItems( this )
        ));
    }


    public User getUser() {
        return user;
    }




}
