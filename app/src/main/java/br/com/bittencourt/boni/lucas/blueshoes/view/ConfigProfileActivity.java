package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.User;

import java.util.ArrayList;

public class ConfigProfileActivity extends FormActivity implements KeyboardUtils.OnSoftInputChangedListener {

    private EditText et_name;
    private RoundedImageView riv_profile;
    private Button bt_send_profile;
    private TextView tv_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        et_name = findViewById(R.id.et_name);
        riv_profile =findViewById(R.id.riv_profile);
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
            if(user.getImage() == null){
                riv_profile.setImageResource( R.drawable.profile_hint);
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
        riv_profile.setEnabled(!status);
        //iv_profile.setEnabled(!status);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if( requestCode == com.nguyenhoanglam.imagepicker.model.Config.RC_PICK_IMAGES
                && resultCode == Activity.RESULT_OK
                && data != null ){

            ArrayList<Image> images = data.getParcelableArrayListExtra( com.nguyenhoanglam.imagepicker.model.Config.EXTRA_IMAGES);

            if( !images.isEmpty() ){
                riv_profile.setImageURI(
                        Uri.parse( images.get(0).getPath() )
                );
            }
        }

        /*
         * Note que em nossa lógica de negócio, se não houver imagem
         * selecionada, o que estiver atualmente presente como imagem
         * de perfil continua sendo a imagem de perfil.
         * */

        /*
         * A invocação a super.onActivityResult() tem que
         * vir após a verificação / obtenção da imagem.
         * */


        super.onActivityResult(requestCode, resultCode, data);
    }

    void callGallery(View view){
        String colorPrimary = ColorUtils.int2ArgbString(
                ColorUtils.getColor(R.color.colorPrimary)
        );
        String colorPrimaryDark = ColorUtils.int2ArgbString(
                ColorUtils.getColor(R.color.colorPrimaryDark)
        );
        String colorText = ColorUtils.int2ArgbString(
                ColorUtils.getColor(R.color.colorText)
        );
        String colorWhite = ColorUtils.int2ArgbString(
                Color.WHITE
        );

        ImagePicker
                .with( this ) /* Inicializa a ImagePicker API com um context (Activity ou Fragment) */
                .setToolbarColor( colorPrimary )
                .setStatusBarColor( colorPrimaryDark )
                .setToolbarTextColor( colorText )
                .setToolbarIconColor( colorText )
                .setProgressBarColor( colorPrimaryDark )
                .setBackgroundColor( colorWhite )
                .setMultipleMode( false )
                .setFolderMode( true )
                .setShowCamera( true )
                .setFolderTitle( getString(R.string.imagepicker_gallery_activity) ) /* Nome da tela de galeria da ImagePicker API (funciona quando FolderMode = true). */
                .setLimitMessage( getString(R.string.imagepicker_selection_limit) )
                .setSavePath( getString(R.string.imagepicker_cam_photos_activity) ) /* Folder das imagens de câmera, tiradas a partir da ImagePicker API. */
                .setKeepScreenOn( true ) /* Mantém a tela acionada enquanto a galeria estiver aberta. */
                .start();
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

        int photoProfileId = riv_profile.getId();
        ConstraintLayout parent = (ConstraintLayout)riv_profile.getParent();
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
