package br.com.bittencourt.boni.lucas.blueshoes.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.AccountSettingItem;
import br.com.bittencourt.boni.lucas.blueshoes.view.ConfigProfileActivity;

public class AccountSettingsItemsDataBase {


    public static List<AccountSettingItem> getItems(Context context) {
        List<AccountSettingItem> items = Arrays.asList(new AccountSettingItem[]{
                new AccountSettingItem(context.getString( R.string.setting_item_profile),context.getString( R.string.setting_item_profile_desc), ConfigProfileActivity.class),
                new AccountSettingItem(context.getString( R.string.setting_item_login),context.getString( R.string.setting_item_login_desc),ConfigProfileActivity.class),
                new AccountSettingItem(context.getString( R.string.setting_item_address),context.getString( R.string.setting_item_address_desc),ConfigProfileActivity.class),
                new AccountSettingItem(context.getString( R.string.setting_item_credit_cards),context.getString( R.string.setting_item_credit_cards_desc ),ConfigProfileActivity.class)
        });

        return items;
    }
}
