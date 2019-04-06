package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import androidx.recyclerview.selection.ItemDetailsLookup;
import br.com.bittencourt.boni.lucas.blueshoes.domain.NavMenuItem;

/*
 * Uma implementação de ItemDetails fornece à biblioteca de seleção
 * acesso a informações sobre um específico item do RecyclerView. Esta
 * classe é um componente chave no controle dos comportamentos da
 * biblioteca de seleção no contexto de uma atividade específica.
 * */
public class NavMenuItemDetails extends ItemDetailsLookup.ItemDetails<Long> {

    private NavMenuItem item = null;
    private int adapterPosition = -1;


    /*
     * Retorna a posição do adaptador do item
     * (ViewHolder.adapterPosition).
     * */
    @Override
    public int getPosition() {
        return adapterPosition;
    }

    /*
     * Retorna a entidade que é a chave de seleção do item.
     * */
    @Nullable
    @Override
    public Long getSelectionKey() {
        return item.getId();
    }

    /*
     * Retorne "true" se o item tiver uma chave de seleção. Se true
     * não for retornado o item em foco (acionado pelo usuário) não
     * será selecionado.
     * */
    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {//neste caso todos são todos (porem é melhor usar a classe de predicado)
        return true;//aki pela logica de negocio se pode selecioar quais ids podem ser selecionados ou não(Lógica de negócio)
    }




    /*==================================================================================*/

    public void setItem(NavMenuItem item) {
        this.item = item;
    }

    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    public NavMenuItem getItem() {
        return item;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }
}
