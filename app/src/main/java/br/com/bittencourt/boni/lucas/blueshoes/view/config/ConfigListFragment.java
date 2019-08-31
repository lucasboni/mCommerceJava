package br.com.bittencourt.boni.lucas.blueshoes.view.config;

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

public  abstract class ConfigListFragment extends ConfigFormFragment{

    private UpdateScreamCallback callbackMainButtonUpdate;
    private UpdateScreamCallback callbackBlockFields ;
    private UpdateScreamCallback callbackRemoveItem ;


    private RecyclerView rv_items;
    protected TextView tv_empty_list;

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_list;
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

    /*
     * Método utilizado para receber os callbacks do adapter
     * do RecyclerView para assim poder atualizar os itens
     * de adapter.
     * */

    public void callbacksToChangeItem(UpdateScreamCallback callbackMainButtonUpdate,
                                      UpdateScreamCallback callbackBlockFields,
                                      UpdateScreamCallback callbackRemoveItem) {
        this.callbackMainButtonUpdate = callbackMainButtonUpdate;
        this.callbackBlockFields = callbackBlockFields;
        this.callbackRemoveItem = callbackRemoveItem;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateFlFormToFullFreeScreen();
        initItems();

    }



    /*
     * Método que inicializa a lista de cartões de crédito.
     * */
    private void initItems(){

        tv_empty_list = getView().findViewById(R.id.tv_empty_list);
        rv_items = getView().findViewById(R.id.rv_items);
        rv_items.setHasFixedSize( false );

        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        rv_items.setLayoutManager(layoutManager);

        RecyclerView.Adapter  adapter =  getRecyclerViewAdapter();
        adapter. registerAdapterDataObserver( new RecyclerViewObserver() );
        rv_items.setAdapter(adapter);
    }


    protected abstract RecyclerView.Adapter getRecyclerViewAdapter();


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

            if (rv_items.getAdapter().getItemCount() == 0)
                tv_empty_list.setVisibility(View.VISIBLE);
            else
                tv_empty_list.setVisibility(View.GONE);
        }
    }


}
