package br.com.bittencourt.boni.lucas.blueshoes.view.config.creditcard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.santalu.maskedittext.MaskEditText;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;

public class ConfigNewCreditCardFragment extends FormFragment implements View.OnFocusChangeListener {

    public static final int TAB_TITLE = R.string.config_credit_cards_tab_new;

    private Button bt_add_credit_card;
    private EditText et_credit_card_security_code;
    private EditText met_credit_card_number;
    private EditText et_credit_card_owner_name;
    private EditText met_credit_card_owner_reg ;
    private Spinner sp_credit_card_expiry_month;
    private EditText et_credit_card_expiry_year;
    private Spinner sp_credit_card_enterprise;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        bt_add_credit_card = view.findViewById(R.id.bt_add_credit_card);
        et_credit_card_security_code = view.findViewById(R.id.et_credit_card_security_code);
        met_credit_card_number = view.findViewById(R.id.met_credit_card_number);
        et_credit_card_owner_name = view.findViewById(R.id.et_credit_card_owner_name);
        met_credit_card_owner_reg  = view.findViewById(R.id.met_credit_card_owner_reg );
        et_credit_card_expiry_year = view.findViewById(R.id.et_credit_card_expiry_year);
        sp_credit_card_expiry_month = view.findViewById(R.id.sp_credit_card_expiry_month);
        sp_credit_card_enterprise = view.findViewById(R.id.sp_credit_card_enterprise);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateFlFormToFullFreeScreen();

        bt_add_credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPasswordDialog();
            }
        });

        et_credit_card_security_code.setOnEditorActionListener( this );
        met_credit_card_owner_reg.setOnFocusChangeListener( this );
    }



    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_new_credit_card;
    }

    @Override
    public void backEndFakeDelay() {
        backEndFakeDelay(
                false,
                getString( R.string.invalid_credit_card )
        );
    }

    @Override
    public void blockFields(Boolean status) {
        met_credit_card_number.setEnabled(!status);
        sp_credit_card_enterprise.setEnabled(!status);
        et_credit_card_owner_name.setEnabled(!status);
        met_credit_card_owner_reg .setEnabled(!status);
        sp_credit_card_expiry_month.setEnabled(!status);
        et_credit_card_expiry_year.setEnabled(!status);
        et_credit_card_security_code.setEnabled(!status);
        bt_add_credit_card.setEnabled(!status);
    }

    @Override
    public void isMainButtonSending(Boolean status) {
        if( status )
            bt_add_credit_card.setText(getString( R.string.add_credit_card_going));
        else
            bt_add_credit_card.setText(getString( R.string.add_credit_card ));
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        String mask = ""; /* Sem máscara. */
        MaskEditText editText = (MaskEditText)view;

        /*
         * Garantindo que o conteúdo em teste terá apenas
         * números.
         * */
        String pattern = "[^0-9 ]";
        String content = editText
                .getText()
                .toString()
                .replaceAll( pattern, "" );

        if( !hasFocus ){
            if(extension_functions.isValidCPF(content)){
                /* Máscara CPF. */
                mask = "###.###.###-##";
            }
            else if(extension_functions.isValidCNPJ(content)){
                /* Máscara CNPJ. */
                mask = "##.###.###/####-##";
            }
        }

        /*
         * Aplicando a nova máscara de acordo com a
         * quantidade de números presentes em campo.
         * */
        editText.setMask(mask);

        /*
         * Para que a nova máscara seja aplicada é preciso
         * forçar uma atualização no campo.
         * */
        editText.setText( content );
    }
}
