package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.content.Context;

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
public class DeliveryAddressesSectionsAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_PAGES = 2;
    private static final int HOST_DELIVERY_ADDRESSES_PAGE_POS = 0;

    private Context context;

    public DeliveryAddressesSectionsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    /*
     * getItem() é invocado para devolver uma instância do
     * fragmento correspondendo a posição (seção/página)
     * informada.
     * */
    @Override
    public Fragment getItem(int position) {
        if(position == HOST_DELIVERY_ADDRESSES_PAGE_POS ){
            return new DeliveryAddressHostFragment();
        }else {
            return new FormNewDeliveryAddressFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == HOST_DELIVERY_ADDRESSES_PAGE_POS) {
            return context.getString(DeliveryAddressesListFragment.TAB_TITLE);
        } else {
            return context.getString(FormNewDeliveryAddressFragment.TAB_TITLE);
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
