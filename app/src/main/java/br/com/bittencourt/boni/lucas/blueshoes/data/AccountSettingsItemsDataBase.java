package br.com.bittencourt.boni.lucas.blueshoes.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.AccountSettingItem;
import br.com.bittencourt.boni.lucas.blueshoes.view.ConfigProfileActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata.ConfigConnectionDataActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard.ConfigCreditCardsActivity;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress.ConfigDeliveryAddressesActivity;

public class AccountSettingsItemsDataBase {


    public static List<AccountSettingItem> getItems(Context context) {
        /**
         * A ORDEM IMPORTA NA HORA DE CHAMAR A TELA
         */
        List<AccountSettingItem> items = Arrays.asList(new AccountSettingItem[]{
                new AccountSettingItem(context.getString(R.string.setting_item_profile), context.getString(R.string.setting_item_profile_desc), ConfigProfileActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_login), context.getString(R.string.setting_item_login_desc), ConfigConnectionDataActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_login), context.getString(R.string.setting_item_login_desc), ConfigProfileActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_address), context.getString(R.string.setting_item_address_desc), ConfigDeliveryAddressesActivity.class),
                new AccountSettingItem(context.getString(R.string.setting_item_credit_cards), context.getString(R.string.setting_item_credit_cards_desc), ConfigCreditCardsActivity.class)
        });

        return items;
    }
}
