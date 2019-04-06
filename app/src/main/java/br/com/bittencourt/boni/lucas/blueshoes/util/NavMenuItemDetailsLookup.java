package br.com.bittencourt.boni.lucas.blueshoes.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;
import br.com.bittencourt.boni.lucas.blueshoes.view.NavMenuItemsAdapter;

/*
 * ItemDetailsLookup permite que a biblioteca de seleção acesse
 * informações sobre os itens do RecyclerView que receberam um
 * MotionEvent. Ele é efetivamente uma factory para instâncias
 * de ItemDetails que são submetidas a backup (ou extraídas)
 * de uma ocorrência de RecyclerView.ViewHolder.
 * */
public class NavMenuItemDetailsLookup extends ItemDetailsLookup<Long> {

    private RecyclerView rvMenuItems;


    public NavMenuItemDetailsLookup(RecyclerView rvMenuItems) {
        this.rvMenuItems = rvMenuItems;
    }

    /*
     * Retorna o ItemDetails para o item sob o evento
     * (MotionEvent) ou nulo caso não haja um.
     * */
    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent event) {
        View view = rvMenuItems.findChildViewUnder( event.getX(), event.getY());//acessa o item que foi selecionado

        if( view != null ){//verifica se é nulo
            RecyclerView.ViewHolder holder = rvMenuItems.getChildViewHolder(view);//recupera o view holder
            return ((NavMenuItemsAdapter.ViewHolder)holder).getItemDetails();//obtem o detalhe
        }

        return null;//se não retorna nulo
    }
}
