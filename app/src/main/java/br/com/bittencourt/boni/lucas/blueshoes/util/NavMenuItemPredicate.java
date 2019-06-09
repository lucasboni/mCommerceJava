package br.com.bittencourt.boni.lucas.blueshoes.util;

import androidx.annotation.NonNull;

import androidx.recyclerview.selection.SelectionTracker;
import br.com.bittencourt.boni.lucas.blueshoes.view.MainActivity;
import br.com.bittencourt.boni.lucas.blueshoes.data.NavMenuItemsDataBase;

/*
 * SelectionTracker.SelectionPredicate é utilizada para definir
 * quais itens poderão ser selecionados e quantos deles.
 *
 * A parametrização deve ser do tipo da chave estável definida
 * em ItemKeyProvider.
 * */
public class NavMenuItemPredicate extends SelectionTracker.SelectionPredicate<Long> {

    private MainActivity activity;

    public NavMenuItemPredicate(MainActivity activity) {
        this.activity = activity;
    }

    /*
     * Retorne true se o item referente a key puder ser definido
     * como nextState.
     * */
    @Override
    public boolean canSetStateForKey(@NonNull Long key, boolean nextState) {
        //pode selecionar qual nao pode ser selecionado, por exxemplo id 2 nao pode ser retornado entao retrona false
        /*
         * A lógica de negócio abaixo foi adotada para que não
         * seja possível deixar o menu gaveta com nenhum item
         * selecionado. Assim, se o status do item acionado for
         * false (nextState = false) e se ele for o item já
         * selecionado, então retornamos false para que o status
         * dele não mude, continue como "selecionado".
         * */
        if( !nextState ){
            long lastItemId = new NavMenuItemsDataBase( activity ).getLastItemId();
            long firstItemLoggedId = new NavMenuItemsDataBase( activity ).getFirstItemLoggedId();

            long sizeNavMenuItems = activity.getSelectNavMenuItems().getSelection().size();
            long sizeNavMenuItemsLogged = activity.getSelectNavMenuItemsLogged().getSelection().size();

            /*
             * Há somente duas situações onde um item pode
             * acionar canSetStateForKey() com nextState sendo
             * false:
             *
             *      1ª - Quando o item está selecionado e então
             *      ele é acionado novamente, para que perca a
             *      seleção;
             *
             *      2ª - Quando é removida a seleção do item
             *      via deselect(), como estamos fazendo na
             *      atividade principal de projeto.
             *
             * No caso da 2ª situação, isso acontece porque
             * temos dois objetos de seleção sendo utilizados,
             * sendo assim, é preciso saber o intervalo do ID
             * do item alvo, pois ele somente não perde a seleção
             * se ele mesmo receber um novo acionamento. Em caso
             * de item de outra lista, ele deve sim perder a
             * seleção.
             * */
            if( (key <= lastItemId && sizeNavMenuItemsLogged == 0)
                    || (key >= firstItemLoggedId && sizeNavMenuItems == 0) ){
                return false;
            }
        }

        return true;
    }

    /*
     * Retorne true se o item referente a position puder ser definido
     * como nextState. NÃO FUNCIONA COMO ESPERADO.
     * */
    @Override
    public boolean canSetStateAtPosition(int position, boolean nexState) {
        return true;//mesma coisa do de cima, só que pela posição(não funciona direito)
    }


    /*
     * Retorne true se puder ter múltipla seleção e false para
     * somente uma seleção.
     * */
    @Override
    public boolean canSelectMultiple() {
        return false;
    }
}
