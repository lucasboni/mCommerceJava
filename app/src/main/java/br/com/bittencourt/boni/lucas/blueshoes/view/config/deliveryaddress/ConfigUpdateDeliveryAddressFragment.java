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
import br.com.bittencourt.boni.lucas.blueshoes.domain.DeliveryAddress;

public class ConfigUpdateDeliveryAddressFragment extends ConfigNewDeliveryAddressFragment {

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
        bt_nu_address.setText(getString( R.string.update_delivery_address ));

        bt_nu_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPasswordDialog();
            }
        });

        fillForm();
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_update_delivery_address;
    }

    private void fillForm(){
        DeliveryAddress address = getArguments().getParcelable(DeliveryAddress.KEY);

        et_street.setText( address.getStreet() );
        et_number.setText( address.getNumber().toString() );
        et_complement.setText( address.getComplement() );
        et_zip_code.setText( address.getZipCode() );
        et_neighborhood.setText( address.getNeighborhood() );
        et_city.setText( address.getCity() );
        sp_state.setSelection( address.getState());
    }

    @Override
    public void backEndFakeDelay(){
        backEndFakeDelay(
                false,
                getString( R.string.invalid_delivery_address )
        );
    }

    @Override
    public void isMainButtonSending(Boolean status){
        if( status )
            bt_nu_address.setText(getString( R.string.update_delivery_address_going ));
        else
            bt_nu_address.setText(getString( R.string.update_delivery_address ));
    }

}
