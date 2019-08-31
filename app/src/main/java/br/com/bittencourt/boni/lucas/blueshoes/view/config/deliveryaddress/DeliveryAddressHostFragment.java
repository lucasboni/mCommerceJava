package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigFormFragment;

/*
 * Fragmento com responsabilidade de ser o fragmento
 * host de mais de um fragmento e assim permitir a
 * fácil alternância de fragmentos dentro de uma mesma
 * tela de ViewPager.
 * */
public class DeliveryAddressHostFragment extends ConfigFormFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*
         * É preciso inflar o layout que vai conter
         * os fragmentos.
         * */
        View view = inflater
                .inflate(
                        R.layout.fragment_config_delivery_address_host,
                        container,
                        false
                );

        /*
         * Somente na primeira abertura é que a regra de
         * fragmento inicial, do bloco condicional a seguir,
         * deve ser seguida.
         * */
        if( savedInstanceState == null ){
            FragmentTransaction transaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();

            /*
             * Então, aqui no fragmento root (container),
             * iniciamos com o primeiro fragmento via
             * FragmentTransaction e sem trabalho com pilha
             * de fragmentos.
             * */
            transaction
                    .replace(
                            R.id.fl_root,
                            new DeliveryAddressesListFragment()
                    )
                    .commit();
        }

        return view;

    }

    @Override
    public int getLayoutResourceID() {
        return 0;
    }

    @Override
    public void backEndFakeDelay() {

    }

    @Override
    public void blockFields(Boolean status) {

    }

    @Override
    public void isMainButtonSending(Boolean status) {

    }

    @Override
    public int title() {
        return DeliveryAddressesListFragment.TAB_TITLE;
    }
}
