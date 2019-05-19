package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public abstract class FormEmailAndPasswordActivity extends FormActivity implements KeyboardUtils.OnSoftInputChangedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Com a API KeyboardUtils conseguimos de maneira
         * simples obter o status atual do teclado virtual (aberto /
         * fechado) e assim prosseguir com algoritmos de ajuste de
         * layout.
         * */
        KeyboardUtils.registerSoftInputChangedListener(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.unregisterSoftInputChangedListener(this);
    }

    /* Listeners de clique */
    public void callPrivacyPolicyFragment(View view) {
        Intent intent = new Intent(
                this,
                MainActivity.class);

        /*
         * Para saber qual fragmento abrir quando a
         * MainActivity voltar ao foreground.
         * */
        intent.putExtra(
                MainActivity.FRAGMENT_ID,
                R.id.item_privacy_policy
        );

        /*
         * Removendo da pilha de atividades a primeira
         * MainActivity aberta (e a LoginActivity), para
         * deixar somente a nova MainActivity com uma nova
         * configuração de fragmento aberto.
         * */
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }


    /*
     * Com a API KeyboardUtils conseguimos de maneira
     * simples obter o status atual do teclado virtual (aberto /
     * fechado) e assim prosseguir com algoritmos de ajuste de
     * layout.
     * */
    @Override
    public void onSoftInputChanged(int height) {
        if (isAbleToCallChangePrivacyPolicyConstraints()) {
            changePrivacyPolicyConstraints(
                    KeyboardUtils.isSoftInputVisible(this)
            );
        }
    }


    protected boolean isAbleToCallChangePrivacyPolicyConstraints() {
        return true;
    }


    private void changePrivacyPolicyConstraints(boolean isKeyBoardOpened) {

        TextView tv_privacy_policy = findViewById(R.id.tv_privacy_policy); // busca de dentro do metodo

        if (tv_privacy_policy == null) return;

        int privacyId = tv_privacy_policy.getId();
        ConstraintLayout parent = (ConstraintLayout) tv_privacy_policy.getParent();
        ConstraintSet constraintSet = new ConstraintSet();

        /*
         * Definindo a largura e a altura da View em
         * mudança de constraints, caso contrário ela
         * fica com largura e altura em 0dp.
         * */
        constraintSet.constrainWidth(
                privacyId,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        constraintSet.constrainHeight(
                privacyId,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        /*
         * Centralizando a View horizontalmente no
         * ConstraintLayout.
         * */
        constraintSet.centerHorizontally(
                privacyId,
                ConstraintLayout.LayoutParams.PARENT_ID
        );

        if (isConstraintToSiblingView(isKeyBoardOpened)) {
            setConstraintsRelativeToSiblingView(constraintSet, privacyId);
        } else {
            /*
             * Se o teclado virtual estiver fechado, então
             * mude a configuração da View alvo
             * (tv_privacy_policy) para ficar vinculada ao
             * fundo do ConstraintLayout ancestral.
             * */
            constraintSet.connect(
                    privacyId,
                    ConstraintLayout.LayoutParams.BOTTOM,
                    ConstraintLayout.LayoutParams.PARENT_ID,
                    ConstraintLayout.LayoutParams.BOTTOM
            );
        }

        constraintSet.applyTo(parent);
    }


    abstract boolean isConstraintToSiblingView(boolean isKeyBoardOpened);

    abstract void setConstraintsRelativeToSiblingView(ConstraintSet constraintSet, int privacyId);


}
