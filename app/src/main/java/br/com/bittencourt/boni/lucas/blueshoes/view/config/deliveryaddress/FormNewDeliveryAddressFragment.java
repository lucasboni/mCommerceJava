package br.com.bittencourt.boni.lucas.blueshoes.view.config.deliveryaddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigFormFragment;

public class FormNewDeliveryAddressFragment extends ConfigFormFragment {

    //public final static int TAB_TITLE = R.string.config_delivery_address_tab_new;

    /*
     * A constante abaixo representa a posição
     * deste fragmento no ViewPager. Os
     * posicionamentos em ViewPager começam
     * em 0.
     * */
    public final static int PAGER_POS = 1;

    private Button bt_nu_address;
    private EditText et_street;
    private EditText et_number;
    private EditText et_complement;
    private EditText et_zip_code;
    private EditText et_neighborhood;
    private EditText et_city;
    private Spinner sp_state;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        bt_nu_address = view.findViewById(R.id.bt_nu_address);
        et_street = view.findViewById(R.id.et_street);
        et_number = view.findViewById(R.id.et_number);
        et_complement = view.findViewById(R.id.et_complement);
        et_zip_code = view.findViewById(R.id.et_zip_code);
        et_neighborhood = view.findViewById(R.id.et_neighborhood);
        et_city = view.findViewById(R.id.et_city);
        sp_state = view.findViewById(R.id.sp_state);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateFlFormToFullFreeScreen();

        bt_nu_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * O método mainAction() é invocado no lugar
                 * de callPasswordDialog(), pois aqui não há
                 * necessidade de dialog de senha para a
                 * adição de endereço de entrega.
                 * */
                mainAction();
            }
        });



    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_new_delivery_address;
    }

    @Override
    public void backEndFakeDelay() {
        backEndFakeDelay(
                false,
                getString( R.string.invalid_delivery_address )
        );
    }

    @Override
    public void blockFields(Boolean status) {
        et_street.setEnabled(!status);
        et_number.setEnabled(!status);
        et_complement.setEnabled(!status);
        et_zip_code.setEnabled(!status);
        et_neighborhood.setEnabled(!status);
        et_city.setEnabled(!status);
        sp_state.setEnabled(!status);
        bt_nu_address.setEnabled(!status);
    }

    @Override
    public void isMainButtonSending(Boolean status) {
        if( status )
            bt_nu_address.setText(getString( R.string.add_delivery_address_going));
        else
            bt_nu_address.setText(getString( R.string.add_delivery_address));
    }

    @Override
    public int title() {
        return R.string.config_delivery_address_tab_new;
    }
}
