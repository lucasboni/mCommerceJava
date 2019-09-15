package br.com.bittencourt.boni.lucas.blueshoes.view.shoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.data.AllShoesDataBase;
import br.com.bittencourt.boni.lucas.blueshoes.view.MainActivity;

public class AllShoesListFragment extends Fragment {

        private static final int GRID_COLUMNS = 2;


        private RecyclerView rv_shoes;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(
                        R.layout.fragment_all_shoes_list,
                        container,
                        false
                );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv_shoes = getView().findViewById(R.id.rv_shoes);

        initItems();
    }



    private void initItems(){
        rv_shoes.setHasFixedSize( false );

        GridLayoutManager layoutManager = new GridLayoutManager(
                getActivity(),
                GRID_COLUMNS,
                RecyclerView.VERTICAL,
                false
        );
        rv_shoes.setLayoutManager(layoutManager);

        AllShoesListAdapter adapter = new AllShoesListAdapter( AllShoesDataBase.getItems() );
        rv_shoes.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).updateToolbarTitleInFragment( R.string.all_shoes_list_frag_title );
    }



}
