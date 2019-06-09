package br.com.bittencourt.boni.lucas.blueshoes.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.recyclerview.selection.ItemKeyProvider;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;
/*
 * Subclasse de ItemKeyProvider que fornece acesso a chaves de seleção
 * estáveis, podendo ser de três tipos: Parcelable (e suas
 * subclasses); String e Long.
 * */
public class NavMenuItemKeyProvider extends ItemKeyProvider<Long> {

    private List<NavMenuItem> items;

    /*public NavMenuItemKeyProvider(int scope, List<NavMenuItem> items) {
        super(scope);
        this.items = items;
    }*/

    public NavMenuItemKeyProvider(List<NavMenuItem> items) {
        super(ItemKeyProvider.SCOPE_MAPPED);
        this.items = items;
    }

    /*
     * Retorna a chave de seleção na posição fornecida do adaptador ou
     * então retorna null.
     * */
    @Nullable
    @Override
    public Long getKey(int position) {
        return items.get(position).getId();
    }

    /*
     * Retorna a posição correspondente à chave de seleção, ou
     * RecyclerView.NO_POSITION em caso de null em getKey().
     * */
    @Override
    public int getPosition(@NonNull Long key) {
        for(int position=0;position<items.size();position++){
            if(items.get(position).getId() == key){
                return position;
            }
        }
        return RecyclerView.NO_POSITION;
    }
}
