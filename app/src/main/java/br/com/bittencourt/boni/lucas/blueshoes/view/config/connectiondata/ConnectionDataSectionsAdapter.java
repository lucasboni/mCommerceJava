package br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



/**
 * Um FragmentPagerAdapter que retorna um fragmento correspondendo
 * a uma das sections/tabs/pages.
 *
 * Mesmo que o método getItem() indique que mais de uma instância
 * do mesmo fragmento será criada, na verdade objetos
 * FragmentPagerAdapter mantêm os fragmentos em memória para que
 * eles possam ser utilizados novamente, isso enquanto houver
 * caminho de volta a eles (transição entre Tabs, por exemplo).
 */
public class ConnectionDataSectionsAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_PAGES = 2;
    private static final int  EMAIL_PAGE_POS = 0;

    public ConnectionDataSectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    /*
     * getItem() é invocado para devolver uma instância do
     * fragmento correspondendo a posição (seção/página)
     * informada.
     * */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case EMAIL_PAGE_POS:
                return  new FormEmailFragment();
            default:
                return new FormPasswordFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case EMAIL_PAGE_POS:
                return  FormEmailFragment.TAB_TITLE;
            default:
                return FormPasswordFragment.TAB_TITLE;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
