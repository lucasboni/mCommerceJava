package br.com.bittencourt.boni.lucas.blueshoes.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;

public class NavMenuItemsDataBase {


    public static final String SP_NAME = "SP_NAV_MENU";
    public static final String SP_ITEM_ID_KEY = "item-id";
    public static final String SP_IS_ACTIVITY_KEY = "is-activity";


    private SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
        );
    }

    /*
     * Salva o ID do último item de menu selecionado que
     * aciona um fragmento.
     * */
    public void saveLastSelectedItemFragmentID(Context context,Long itemID){
        SharedPreferences sp = getSP( context );
        sp.edit().putLong( SP_ITEM_ID_KEY, itemID ).apply();
    }

    /*
     * Retorna o ID do último item de menu selecionado que
     * aciona um fragmento.
     * */
    public Long getLastSelectedItemFragmentID(Context context)  {
        SharedPreferences sp = getSP( context );
        return sp.getLong( SP_ITEM_ID_KEY, 0 );
    }

    /*
     * Salva se o último item de menu acionado foi ou não
     * um item que aciona uma atividade.
     * */
    public void saveIsActivityItemFired(Context context, Boolean isActivity){
        SharedPreferences sp = getSP( context );
        sp.edit()
                .putBoolean( SP_IS_ACTIVITY_KEY, isActivity )
                .apply();
    }

    /*
     * Informa se o último item de menu acionado foi ou não
     * um item que aciona uma atividade.
     * */
    public Boolean wasActivityItemFired( Context context )  {
        SharedPreferences sp = getSP( context );
        return sp.getBoolean( SP_IS_ACTIVITY_KEY, false );
    }


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
