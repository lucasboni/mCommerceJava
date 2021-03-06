package br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigFormActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigSectionsAdapter;

public class CreditCardsActivity extends ConfigFormActivity {


    @Override
    public FragmentPagerAdapter getSectionsAdapter() {
        return new ConfigSectionsAdapter(
                this,
                getSupportFragmentManager(),
                new CreditCardsListFragment(),
                new FormCreditCardFragment()
        );
    }
}
