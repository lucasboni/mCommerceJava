package br.com.bittencourt.boni.lucas.blueshoes.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;

public class NavMenuItemsDataBase {

    //private Context context;

    /*
     * Itens de menu gaveta que sempre estarão presentes,
     * independente do status do usuário (conectado ou
     * não).
     * */
    private List<NavMenuItem> items;

    /*
     * Itens de menu gaveta que estarão presentes somente
     * quando o usuário estiver conectado.
     * */
    private List<NavMenuItem> itemsLogged;

    public NavMenuItemsDataBase(Context context) {
        //this.context = context;
        items = Arrays.asList(new NavMenuItem[]{
                new NavMenuItem(R.id.item_all_shoes,context.getString(R.string.item_all_shoes)),
                new NavMenuItem(R.id.item_flip_flops,context.getString(R.string.item_flip_flops)),
                new NavMenuItem(R.id.item_cleats,context.getString(R.string.item_cleats)),
                new NavMenuItem(R.id.item_sandals,context.getString(R.string.item_sandals)),
                new NavMenuItem(R.id.item_ballet_shoes,context.getString(R.string.item_ballet_shoes)),
                new NavMenuItem(R.id.item_suit_shoes,context.getString(R.string.item_suit_shoes)),
                new NavMenuItem(R.id.item_shoes,context.getString(R.string.item_shoes)),
                new NavMenuItem(R.id.item_performance_shoes,context.getString(R.string.item_performance_shoes)),
                new NavMenuItem(R.id.item_contact,context.getString(R.string.item_contact),R.drawable.ic_email_black_24dp),
                new NavMenuItem(R.id.item_about,context.getString(R.string.item_about),R.drawable.ic_domain_black_24dp),
                new NavMenuItem(R.id.item_privacy_policy,context.getString(R.string.item_privacy_policy),R.drawable.ic_shield_lock_black_24dp)
        });

        itemsLogged  = Arrays.asList(new NavMenuItem[]{
                new NavMenuItem(R.id.item_my_orders,context.getString(R.string.item_my_orders),R.drawable.ic_package_variant_closed_black_24dp),
                new NavMenuItem(R.id.item_settings,context.getString(R.string.item_settings),R.drawable.ic_settings_black_24dp),
                new NavMenuItem(R.id.item_sign_out,context.getString(R.string.item_sign_out),R.drawable.ic_exit_run_black_24dp)
        });
    }


    public List<NavMenuItem> getItems() {
        return items;
    }

    public List<NavMenuItem> getItemsLogged() {
        return itemsLogged;
    }

    public long getLastItemId(){
        return items.get(items.size()-1).getId();
    }

    public long getFirstItemLoggedId(){
        return itemsLogged.get(0).getId();
    }

}
