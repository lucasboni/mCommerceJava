package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.data.DeliveryAddressesDataBase;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard.UpdateScreamCallback;

public class ConfigDeliveryAddressesListFragment extends FormFragment {

    public final static int TAB_TITLE = R.string.config_delivery_addresses_tab_list;

    private UpdateScreamCallback callbackMainButtonUpdate;
    private UpdateScreamCallback callbackBlockFields;
    private UpdateScreamCallback callbackRemoveItem;

    private RecyclerView rv_delivery_addresses;
    private TextView tv_empty_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        rv_delivery_addresses = view.findViewById(R.id.rv_delivery_addresses);
        tv_empty_list = view.findViewById(R.id.tv_empty_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateFlFormToFullFreeScreen();
        initItems();
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_delivery_addresses_list;
    }

    @Override
    public void backEndFakeDelay() {
        backEndFakeDelay(
                true,
                getString(R.string.delivery_address_removed)
        );
    }

    @Override
    public void blockFields(Boolean status) {
        callbackBlockFields.action(status);
    }

    @Override
    public void isMainButtonSending(Boolean status) {
        callbackMainButtonUpdate.action(status);
        callbackRemoveItem.action(status);
    }


    public void callbacksToUpdateItem(UpdateScreamCallback callbackMainButtonUpdate,
                                      UpdateScreamCallback callbackBlockFields,
                                      UpdateScreamCallback callbackRemoveItem) {
        this.callbackMainButtonUpdate = callbackMainButtonUpdate;
        this.callbackBlockFields = callbackBlockFields;
        this.callbackRemoveItem = callbackRemoveItem;
    }

    /*
     * Método que inicializa a lista de endereços de entrega.
     * */
    private void initItems() {
        rv_delivery_addresses.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_delivery_addresses.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ConfigDeliveryAddressesListItemsAdapter(
                DeliveryAddressesDataBase.getItems(), this);
        adapter.registerAdapterDataObserver(new RecyclerViewObserver());
        rv_delivery_addresses.setAdapter(adapter);
    }

    /*
     * Com o RecyclerView.AdapterDataObserver é possível
     * escutar o tamanho atual da lista de itens vinculada
     * ao RecyclerView e caso essa lista esteja vazia, então
     * podemos apresentar uma mensagem ao usuário informando
     * sobre a lista vazia.
     * */
    private class RecyclerViewObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            if (rv_delivery_addresses.getAdapter().getItemCount() == 0) {
                tv_empty_list.setVisibility(View.VISIBLE);
            } else {
                tv_empty_list.setVisibility(View.GONE);
            }
        }
    }
}
