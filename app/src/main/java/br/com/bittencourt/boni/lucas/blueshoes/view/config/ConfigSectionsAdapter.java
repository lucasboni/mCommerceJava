package br.com.bittencourt.boni.lucas.blueshoes.view.config;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata.FormEmailFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata.FormPasswordFragment;

public class ConfigSectionsAdapter extends FragmentPagerAdapter {


    private static final int TOTAL_PAGES  = 2;
    private static final int  FIRST_PAGE_POS  = 0;
    private static final int  SECOND_PAGE_POS  = 1;

    private ConfigFormFragment[] fragments;
    private Context context;

    public ConfigSectionsAdapter(Context context,FragmentManager fm, ConfigFormFragment... fragments) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    /*
     * getItem() é invocado para devolver uma instância do
     * fragmento correspondendo a posição (seção/página)
     * informada.
     * */
    @Override
    public Fragment getItem(int position) {
        if(position == FIRST_PAGE_POS ){
            return fragments[FIRST_PAGE_POS];
        }else{
            return fragments[SECOND_PAGE_POS];
        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == FIRST_PAGE_POS) {
            return context.getString(fragments[FIRST_PAGE_POS].title());
        }
        return context.getString(fragments[SECOND_PAGE_POS].title());
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
