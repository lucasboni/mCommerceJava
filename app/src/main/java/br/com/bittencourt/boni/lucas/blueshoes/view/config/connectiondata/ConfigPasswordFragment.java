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
import androidx.annotation.StringRes;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;
import br.com.bittencourt.boni.lucas.blueshoes.view.FormFragment;

public class ConfigPasswordFragment extends FormFragment {

    //public static final String TAB_TITLE = Resources.getSystem().getString(R.string.config_connection_data_tab_password);//nao funcionou
    public static final String TAB_TITLE ="SENHA";

    private EditText et_new_password;
    private EditText et_new_password_confirm;
    private Button bt_update_password;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        et_new_password = view.findViewById(R.id.et_new_password);
        et_new_password_confirm = view.findViewById(R.id.et_new_password_confirm);
        bt_update_password = view.findViewById(R.id.bt_update_password);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        bt_update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPasswordDialog();
            }
        });

        extension_functions.isValidPassword(et_new_password, getString(R.string.invalid_password ));
        extension_functions.isGernicValidation(et_new_password_confirm, new extension_functions.Validater() {
            @Override
            public boolean validar(CharSequence content) {
                /*
                 * O toString() em et_new_password.text.toString() é
                 * necessário, caso contrário a validação falha
                 * mesmo quando é para ser ok.
                 * */
                return (!et_new_password.getText().toString().isEmpty()
                        && content.toString().equals(et_new_password.getText().toString()))
                        || et_new_password.getText().toString().isEmpty();

            }
        },getString( R.string.invalid_confirmed_password));


        et_new_password_confirm.setOnEditorActionListener(this);
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_config_password;
    }

    @Override
    public void backEndFakeDelay(){
        backEndFakeDelay(
                false,
                getString( R.string.invalid_password)
        );
    }

    @Override
    public void blockFields(Boolean status){
        et_new_password.setEnabled(!status);
        et_new_password_confirm.setEnabled(!status);
        bt_update_password.setEnabled(!status);
    }

    @Override
    public void isMainButtonSending(Boolean status){
        if( status )
            bt_update_password.setText(getString( R.string.update_password_going  ));
        else
            bt_update_password.setText(getString( R.string.update_password ));
    }

}
