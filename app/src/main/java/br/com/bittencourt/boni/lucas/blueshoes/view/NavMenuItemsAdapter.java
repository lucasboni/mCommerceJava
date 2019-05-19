package br.com.bittencourt.boni.lucas.blueshoes.view;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.selection.SelectionTracker;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;
import br.com.bittencourt.boni.lucas.blueshoes.util.NavMenuItemDetails;

public class NavMenuItemsAdapter extends RecyclerView.Adapter<NavMenuItemsAdapter.ViewHolder> {

    private List<NavMenuItem> items;

    private SelectionTracker<Long> selectionTracker;

    public NavMenuItemsAdapter(List<NavMenuItem> items) {
        this.items = items;
    }

    public void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    /*public SelectionTracker<Long> getSelectionTracker() {
        return selectionTracker;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nav_menu_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvLabel;

        private NavMenuItemDetails itemDetails;//armazena referencia

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvLabel = itemView.findViewById(R.id.tv_label);

            itemDetails = new NavMenuItemDetails();
        }

        public void setData(NavMenuItem item) {
            tvLabel.setText(item.getLabel());

            itemDetails.setItem(item);
            itemDetails.setAdapterPosition(getAdapterPosition());

            if(selectionTracker.isSelected(itemDetails.getSelectionKey())){//verifica se esta selecionado e atribui a cor correspondente
                itemView.setBackgroundColor( ContextCompat.getColor( itemView.getContext(),R.color.colorNavItemSelected));
            } else{
                itemView.setBackgroundColor( Color.TRANSPARENT );
            }



            if (item.getIcon() != NavMenuItem.DEFAULT_ICON_ID) {
                ivIcon.setImageResource(item.getIcon());
                ivIcon.setVisibility(View.VISIBLE);
            } else {
                ivIcon.setVisibility(View.GONE);
            }
        }

        public NavMenuItemDetails getItemDetails() {
            return itemDetails;
        }
    }

}
