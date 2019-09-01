package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.DeliveryAddress;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.UpdateScreamCallback;

public class DeliveryAddressesListItemsAdapter extends RecyclerView.Adapter<DeliveryAddressesListItemsAdapter.ViewHolder> {

    private List<DeliveryAddress> items;
    private DeliveryAddressesListFragment fragment;

    public DeliveryAddressesListItemsAdapter(List<DeliveryAddress> items, DeliveryAddressesListFragment fragment) {
        this.items = items;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public DeliveryAddressesListItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.delivery_address_item,
                        parent,
                        false
                );

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAddressesListItemsAdapter.ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView tvStreet;
        private TextView tvNumber;
        private TextView tvZipCode;
        private TextView tvNeighborhood;
        private TextView tvCity;
        private TextView tvState;
        private TextView tvComplement;
        private Button btUpdate;
        private Button btRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStreet = itemView.findViewById(R.id.tv_street);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvZipCode = itemView.findViewById(R.id.tv_zip_code);
            tvNeighborhood = itemView.findViewById(R.id.tv_neighborhood);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvState = itemView.findViewById(R.id.tv_state);
            tvComplement = itemView.findViewById(R.id.tv_complement);

            btUpdate = itemView.findViewById(R.id.bt_update);
            btUpdate.setOnClickListener(this);

            btRemove = itemView.findViewById(R.id.bt_remove);
            btRemove.setOnClickListener(this);
        }

        public void setData(DeliveryAddress item) {

            tvStreet.setText(item.getStreet());
            tvNumber.setText(item.getNumber().toString());
            tvZipCode.setText(item.getZipCode());
            tvNeighborhood.setText(item.getNeighborhood());
            tvCity.setText(item.getCity());

            /*
             * Assim que adicionarmos o parâmetro "fragment" ao
             * construtor de ConfigDeliveryAddressListItemsAdapter
             * o Android Studio IDE vai parar de apontar a linha
             * abaixo como uma linha problemática.
             **/
            tvState.setText(item.getStateName(fragment.getContext()));
            tvComplement.setText(item.getComplement());
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
            int selectedItem = getAdapterPosition();

            if (view.getId() == btRemove.getId()) {
                toRemove(selectedItem);
            }else{
                toUpdate( selectedItem );
            }
        }

        private void toRemove(final int position) {

            fragment.callbacksToChangeItem(
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            btUpdate.setEnabled(!status);
                            btRemove.setEnabled(!status);
                        }
                    },
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            if (status)
                                btRemove.setText(fragment.getString(R.string.remove_item_going));
                            else
                                btRemove.setText(fragment.getString(R.string.remove_item));
                        }
                    },
                    new UpdateScreamCallback() {
                        @Override
                        public void action(boolean status) {
                            if (!status) {
                                items.remove(position);
                                notifyItemRemoved(position);
                            }
                        }
                    }
            );
            fragment.callPasswordDialog();
        }

        private void toUpdate( Integer position ){

            Fragment updateFrag = new UpdateDeliveryAddressFragmentForm();

            /*
             * Colocando como dado de transição o item selecionado para
             * atualização.
             * */
            Bundle bundle = new Bundle();
            bundle.putParcelable(
                    DeliveryAddress.KEY,
                    items.get(position)
            );
            updateFrag.setArguments(bundle);

            FragmentTransaction transaction = fragment
                    .getFragmentManager()
                    .beginTransaction();

            /*
             * O acesso ao FrameLayout root volta a ocorrer para que
             * seja possível o replace de fragmentos dentro da mesma
             * janela de ViewPager.
             * */
            transaction
                    .replace(
                            R.id.fl_root,
                            updateFrag
                    );

            /*
             * Com o setTransition() e addToBackStack() nós estamos,
             * respectivamente permitindo uma transição entre fragmentos
             * e os colocando em uma pilha de fragmentos para que seja
             * possível voltar ao fragmento anteriormente apresentado
             * na mesma janela de ViewPager.
             * */
            transaction
                    .setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
                    .addToBackStack( null )
                    .commit();
        }
    }
}
