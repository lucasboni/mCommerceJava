package br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.CreditCard;

public class ConfigCreditCardsListItemsAdapter extends RecyclerView.Adapter<ConfigCreditCardsListItemsAdapter.ViewHolder> {

    private List<CreditCard> items;
    private ConfigCreditCardsListFragment fragment;

    public ConfigCreditCardsListItemsAdapter(ConfigCreditCardsListFragment fragment,List<CreditCard> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull
    @Override
    public ConfigCreditCardsListItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater
                .from( parent.getContext() )
                .inflate(
                        R.layout.credit_card_item,
                        parent,
                        false
                );

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfigCreditCardsListItemsAdapter.ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvEnterprise;
        private TextView tvNumber;
        private TextView tvOwnerName;
        private Button btRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEnterprise = itemView.findViewById(R.id.tv_enterprise_label);
            tvNumber = itemView.findViewById(R.id.tv_number_label);
            tvOwnerName = itemView.findViewById(R.id.tv_owner_name_label);
            btRemove = itemView.findViewById(R.id.bt_remove);
            btRemove.setOnClickListener(this);
        }

        public void setData(CreditCard item) {
            tvEnterprise.setText( item.getEnterprise() );
            tvNumber.setText( item.getNumberAsHidden() );
            tvOwnerName.setText( item.getOwnerFullNameAsHidden() );
        }

        @Override
        public void onClick(View view) {
            /*
             * É preciso salvar em uma nova propriedade a posição do
             * item selecionado, pois o valor de adapterPosition está
             * sempre sendo atualizado e isso, o acesso a adapterPosition
             * diretamente dentro do callback, poderia ocasionar em
             * uma Exception.
             * */
            final int selectedItem = getAdapterPosition();

            fragment.callbacksToUpdateItem(
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            if( status )
                                btRemove.setText(fragment.getString( R.string.remove_item_going ));
                            else
                                btRemove.setText(fragment.getString( R.string.remove_item ));
                        }
                    },
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            btRemove.setEnabled(!status);
                        }
                    },
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            if( !status ){
                                items.remove( selectedItem );
                                notifyItemRemoved( selectedItem );
                            }
                        }
                    }
            );

            fragment.callPasswordDialog();
        }
    }
}
