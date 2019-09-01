package br.com.bittencourt.boni.lucas.blueshoes.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.AccountSettingItem;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata.ConnectDataActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.profile.ProfileActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard.CreditCardsActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress.DeliveryAddressesActivity;

public class AccountSettingsItemsDataBase {


    public static List<AccountSettingItem> getItems(Context context) {
        /**
         * A ORDEM IMPORTA NA HORA DE CHAMAR A TELA
         */
        List<AccountSettingItem> items = Arrays.asList(new AccountSettingItem[]{
                new AccountSettingItem(context.getString(R.string.setting_item_profile), context.getString(R.string.setting_item_profile_desc), ProfileActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_login), context.getString(R.string.setting_item_login_desc), ConnectDataActivity.class),
                /*new AccountSettingItem(context.getString(R.string.setting_item_login), context.getString(R.string.setting_item_login_desc), ProfileActivity.class),*/
                new AccountSettingItem(context.getString(R.string.setting_item_address), context.getString(R.string.setting_item_address_desc), DeliveryAddressesActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_credit_cards), context.getString(R.string.setting_item_credit_cards_desc), CreditCardsActivity.class)
        });

        return items;
    }
}
