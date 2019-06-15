package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.User;

public class ConfigProfileActivity extends FormActivity implements KeyboardUtils.OnSoftInputChangedListener {

    private EditText et_name;
    private ImageView iv_profile;
    private Button bt_send_profile;
    private TextView tv_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        et_name = findViewById(R.id.et_name);
        iv_profile =findViewById(R.id.iv_profile);
        bt_send_profile =findViewById(R.id.bt_send_profile);
        tv_name =findViewById(R.id.tv_name);


        //et_name.setOnEditorActionListener(this);


        /*
         * Name é um dos dados de banco de dados, e campo de
         * formulário, que nunca poderá estar vazio.
         * */
        User user = getIntent().getParcelableExtra( User.KEY );
        if(user != null) {
            if (user.getName().length() > 1) {
                et_name.setText(user.getName());
            } else {
                et_name.setText(getString(R.string.invalid_name));
            }
        }



        KeyboardUtils.registerSoftInputChangedListener(this,this);
    }

    @Override
    protected void onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener( this );
        super.onDestroy();
    }

    @Override
    protected int getLayoutResourceID() {
        return  R.layout.content_config_profile;
    }

    @Override
    void backEndFakeDelay() {
        backEndFakeDelay(false,getString( R.string.invalid_config_profile));
    }

    @Override
    void blockFields(Boolean status) {
        iv_profile.setEnabled(!status);
        et_name.setEnabled(!status);
        bt_send_profile.setEnabled(!status);
    }

    @Override
    void isMainButtonSending(Boolean status) {
        String msg;
        if( status )
            msg = getString( R.string.config_profile_going );
        else
            msg = getString( R.string.config_profile );

        bt_send_profile.setText(msg);
    }


    void callGallery(View view){
        Toast
                .makeText( this, "TODO", Toast.LENGTH_SHORT )
                .show();
    }

    @Override
    public void onSoftInputChanged(int height) {
        if( isAbleToCallChangeTargetViewConstraints()){
            changeTargetViewConstraints( KeyboardUtils.isSoftInputVisible(this));
        }
    }

    private boolean isAbleToCallChangeTargetViewConstraints(){
        return true;
    }


    private void changeTargetViewConstraints( Boolean isKeyBoardOpened ){

        int photoProfileId = iv_profile.getId();
        ConstraintLayout parent = (ConstraintLayout)iv_profile.getParent();
        ConstraintSet constraintSet = new ConstraintSet();
        int size = (int)(108 * ScreenUtils.getScreenDensity());

        /*
         * Definindo a largura e a altura da View em
         * mudança de constraints, caso contrário ela
         * fica com largura e altura em 0dp.
         * */
        constraintSet.constrainWidth(
                photoProfileId,
                size
        );
        constraintSet.constrainHeight(
                photoProfileId,
                size
        );

        /*
         * Centralizando a View horizontalmente no
         * ConstraintLayout.
         * */
        constraintSet.centerHorizontally(
                photoProfileId,
                ConstraintLayout.LayoutParams.PARENT_ID
        );

        if( isConstraintToSiblingView( isKeyBoardOpened ) ){
            setConstraintsRelativeToSiblingView( constraintSet, photoProfileId );
        }
        else{
            constraintSet.connect(
                    photoProfileId,
                    ConstraintLayout.LayoutParams.TOP,
                    ConstraintLayout.LayoutParams.PARENT_ID,
                    ConstraintLayout.LayoutParams.TOP
            );
        }

        constraintSet.applyTo( parent );
    }

    private boolean isConstraintToSiblingView( Boolean isKeyBoardOpened) {
        return isKeyBoardOpened || ScreenUtils.isLandscape();
    }

    private void setConstraintsRelativeToSiblingView(ConstraintSet constraintSet,Integer targetViewId) {
        constraintSet.connect(
                targetViewId,
                ConstraintLayout.LayoutParams.BOTTOM,
                tv_name.getId(),
                ConstraintLayout.LayoutParams.TOP,
                (int)(30 * ScreenUtils.getScreenDensity())
        );
    }
}
