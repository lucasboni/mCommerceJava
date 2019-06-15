package br.com.bittencourt.boni.lucas.blueshoes.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.AccountSettingItem;
import br.com.bittencourt.boni.lucas.blueshoes.domain.User;

public class AccountSettingsItemsAdapter extends RecyclerView.Adapter<AccountSettingsItemsAdapter.ViewHolder>{

    private List<AccountSettingItem> items;

    public AccountSettingsItemsAdapter(List<AccountSettingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View layout = LayoutInflater
                .from( parent.getContext() )
                .inflate(
                        R.layout.account_settings_item,
                        parent,
                        false
                );

        return new ViewHolder( layout );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData( items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvLabel;
        private TextView tvDescription;

        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById( R.id.tv_label );
            tvDescription = itemView.findViewById( R.id.tv_description );
            itemView.setOnClickListener( this );
        }

        protected void setData(AccountSettingItem item){
            tvLabel.setText(item.getLabel());
            tvDescription.setText(item.getDescription());
        }

        @Override
        public void onClick(View view) {
            AccountSettingsActivity activity = (AccountSettingsActivity)view.getContext();
            User user = activity.getUser();
            Intent intent = new Intent(
                    activity,
                    items.get(getAdapterPosition()).getActivityClass()
            );

            intent.putExtra(User.KEY, user );
            activity.startActivity( intent );
        }
    }



}
