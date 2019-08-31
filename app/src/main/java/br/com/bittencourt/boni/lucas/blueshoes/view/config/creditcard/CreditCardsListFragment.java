package br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard;

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
import br.com.bittencourt.boni.lucas.blueshoes.data.CreditCardsDataBase;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigListFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.UpdateScreamCallback;

public class CreditCardsListFragment extends ConfigListFragment {


    /*private UpdateScreamCallback callbackMainButtonUpdate;
    private UpdateScreamCallback callbackBlockFields ;
    private UpdateScreamCallback callbackRemoveItem ;


    private RecyclerView rv_credit_cards;*/
    //private TextView tv_empty_list;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //rv_credit_cards = view.findViewById(R.id.rv_credit_cards);
        //tv_empty_list = view.findViewById(R.id.tv_empty_list);

        return view;
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_empty_list.setText( R.string.credit_card_list_empty );
        ///updateFlFormToFullFreeScreen();
        //initItems();
    }

    /*@Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_credit_cards_list;
    }*/

    @Override
    public void backEndFakeDelay() {
        backEndFakeDelay(
                true,
                getString( R.string.credit_card_removed )
        );
    }

   /* @Override
    public void blockFields(Boolean status) {
        callbackBlockFields.action(status);
    }

    @Override
    public void isMainButtonSending(Boolean status) {
        callbackMainButtonUpdate.action(status);
        callbackRemoveItem.action(status);
    }*/


    /*
     * Método que inicializa a lista de cartões de crédito.
     * */
    /*private void initItems(){
        rv_credit_cards.setHasFixedSize( false );

        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        rv_credit_cards.setLayoutManager(layoutManager);

        CreditCardsListItemsAdapter adapter = new CreditCardsListItemsAdapter(
                this,
                CreditCardsDataBase.getItems()
        );
        adapter.registerAdapterDataObserver(new RecyclerViewObserver());
        rv_credit_cards.setAdapter(adapter);
    }*/

    @Override
    protected RecyclerView.Adapter getRecyclerViewAdapter() {
        return new CreditCardsListItemsAdapter(this,  CreditCardsDataBase.getItems());
    }


    /*public void callbacksToUpdateItem(UpdateScreamCallback callbackMainButtonUpdate,
                                      UpdateScreamCallback callbackBlockFields,
                                      UpdateScreamCallback callbackRemoveItem) {
        this.callbackMainButtonUpdate = callbackMainButtonUpdate;
        this.callbackBlockFields = callbackBlockFields;
        this.callbackRemoveItem = callbackRemoveItem;
    }*/

    @Override
    public int title() {
        return R.string.config_credit_cards_tab_list;
    }


    /*
     * Com o RecyclerView.AdapterDataObserver é possível
     * escutar o tamanho atual da lista de itens vinculada
     * ao RecyclerView e caso essa lista esteja vazia, então
     * podemos apresentar uma mensagem ao usuário informando
     * sobre a lista vazia.
     * */
    /*public class RecyclerViewObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved( positionStart, itemCount );

            if(rv_credit_cards.getAdapter().getItemCount() == 0){
                tv_empty_list.setVisibility(View.VISIBLE);
            }else{
                tv_empty_list.setVisibility(View.GONE);
            }
        }
    }*/

}
