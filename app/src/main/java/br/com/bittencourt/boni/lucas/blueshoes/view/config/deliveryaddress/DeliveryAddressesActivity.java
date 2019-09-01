package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigFormActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigSectionsAdapter;

public class DeliveryAddressesActivity extends ConfigFormActivity {

    @Override
    public FragmentPagerAdapter getSectionsAdapter() {
        return new ConfigSectionsAdapter(
                this,
                getSupportFragmentManager(),
                new DeliveryAddressHostFragment(),
                new FormNewDeliveryAddressFragment()
        );
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if( item.getItemId() == android.R.id.home ){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();

        /*
         * Se houver algum fragmento em pilha de fragmentos
         * e o fragmento atual em tela não for o fragment de
         * formulário de novo endereço de entrega, então o
         * próximo fragmento da pilha de fragmentos é que
         * deve ser apresentado.
         *
         * Caso contrário, volte a atividade anterior via
         * finish().
         * */
        if( fragmentsInStack > 0
                && isNewDeliveryAddressFormNotInScreen() ){
            getSupportFragmentManager().popBackStack();
        }
        else {
            finish();
        }
    }

    private boolean isNewDeliveryAddressFormNotInScreen() {
        return view_pager.getCurrentItem() != FormNewDeliveryAddressFragment.PAGER_POS;
    }
}
