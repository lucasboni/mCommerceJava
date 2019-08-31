package br.com.bittencourt.boni.lucas.blueshoes.view.config.connectiondata;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;
import br.com.bittencourt.boni.lucas.blueshoes.view.config.ConfigFormFragment;

public class FormEmailFragment extends ConfigFormFragment {

    //public static final String TAB_TITLE =  Resources.getSystem().getString(R.string.config_connection_data_tab_email);
    //public static final String TAB_TITLE = "E-MAIL";

    private EditText et_new_email_confirm;
    private EditText et_current_email;
    private EditText et_new_email;
    private Button bt_update_email_login;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        et_new_email_confirm = view.findViewById(R.id.et_new_email_confirm);
        et_current_email = view.findViewById(R.id.et_current_email);
        et_new_email = view.findViewById(R.id.et_new_email);
        bt_update_email_login = view.findViewById(R.id.bt_update_email_login);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        bt_update_email_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPasswordDialog();
            }
        });

        extension_functions.isValidEmail(et_current_email, getString(R.string.invalid_email));
        extension_functions.isValidEmail(et_new_email, getString(R.string.invalid_email));
        extension_functions.isGernicValidation(et_new_email_confirm, new extension_functions.Validater() {
            @Override
            public boolean validar(CharSequence content) {
                /*
                 * O toString() em et_new_email.text.toString() é
                 * necessário, caso contrário a validação falha
                 * mesmo quando é para ser ok.
                 * */
                return (!et_new_email.getText().toString().isEmpty()
                        && content.toString().equals(et_new_email.getText().toString()))
                        || et_new_email.getText().toString().isEmpty();

            }
        },getString( R.string.invalid_confirmed_email));


        et_new_email_confirm.setOnEditorActionListener(this);
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_email;
    }

    @Override
    public void backEndFakeDelay(){
        backEndFakeDelay(
                false,
                getString( R.string.invalid_sign_up_email )
        );
    }

    @Override
    public void blockFields(Boolean status){
        et_current_email.setEnabled(!status);
        et_new_email.setEnabled(!status);
        et_new_email_confirm.setEnabled(!status);
        bt_update_email_login.setEnabled(!status);
    }

    @Override
     public void isMainButtonSending(Boolean status){
        if( status )
            bt_update_email_login.setText(getString( R.string.update_email_login_going ));
        else
            bt_update_email_login.setText(getString( R.string.update_email_login));
    }

    @Override
    public int title() {
        return R.string.config_connection_data_tab_email;
    }
}
