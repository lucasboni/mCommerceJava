package br.com.bittencourt.boni.lucas.blueshoes.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;
import com.google.android.material.snackbar.Snackbar;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.util.extension_functions;

abstract public class FormFragment extends Fragment implements TextView.OnEditorActionListener {

    private FrameLayout fl_proxy_container;
    private ViewGroup containerForm;
    private FrameLayout fl_form;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewContainer = (ViewGroup) inflater
                .inflate(
                        R.layout.fragment_form,
                        container,
                        false
                );

        /*
         * Colocando a View de um arquivo XML como View filha
         * do item indicado no terceiro argumento.
         * */
        View.inflate(
                getActivity(),
                getLayoutResourceID(),
                (ViewGroup) viewContainer.findViewById(R.id.fl_form)
        );


        fl_form = viewContainer.findViewById(R.id.fl_form);
        fl_proxy_container = viewContainer.findViewById(R.id.fl_proxy_container);
        containerForm = (ViewGroup) fl_proxy_container.getParent();

        return viewContainer;
    }


    /*
     * Caso o usuário toque no botão "Done" do teclado virtual
     * ao invés de tocar no botão "Entrar". Mesmo assim temos
     * de processar o formulário.
     * */
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        callPasswordDialog();
        return false;
    }


    abstract public int getLayoutResourceID();


    /*
     * Apresenta a tela de bloqueio que diz ao usuário que
     * algo está sendo processado em background e que ele
     * deve aguardar.
     * */
    protected void showProxy(boolean status) {
        if (status) {
            fl_proxy_container.setVisibility(View.VISIBLE);
        } else {
            fl_proxy_container.setVisibility(View.GONE);
        }
    }


    /*
     * Método responsável por apresentar um SnackBar com as
     * corretas configurações de acordo com o feedback do
     * back-end Web.
     * */
    private void snackBarFeedback(ViewGroup viewContainer, boolean status, String message) {

        Snackbar snackBar = Snackbar
                .make(
                        viewContainer,
                        message,
                        Snackbar.LENGTH_LONG
                );
        View snackBarView = snackBar.getView();
        /*
         * Criando o objeto Drawable que entrará como ícone
         * inicial no texto do SnackBar.
         * */
        int iconResource;
        if (status) {
            iconResource = R.drawable.ic_check_black_18dp;
        } else {
            iconResource = R.drawable.ic_close_black_18dp;
        }

        Drawable img = ResourcesCompat.getDrawable(getResources(), iconResource, null);
        img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());


        int iconColor;

        if (status) {
            iconColor = ContextCompat.getColor(getActivity(), R.color.colorNavButton);
        } else {
            iconColor = Color.RED;
        }

        img.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP);


        /*
         * Acessando o TextView padrão do SnackBar para assim
         * colocarmos um ícone nele via objeto Spannable.
         * */
        TextView textView = snackBarView.findViewById(R.id.snackbar_text);
        SpannableString spannedText = new SpannableString(textView.getText());

        /*
         * Acessando o TextView padrão do SnackBar para assim
         * colocarmos um ícone nele via objeto Spannable.
         * */

        /*
         * O espaçamento aplicado como parte do argumento
         * de SpannableString() é para que haja um espaço
         * entre o ícone e o texto do SnackBar, como
         * informado em protótipo estático.
         * */
        spannedText.setSpan(
                new ImageSpan(img, ImageSpan.ALIGN_BOTTOM),
                0,
                1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        textView.setText(spannedText, TextView.BufferType.SPANNABLE);
        snackBar.show();
    }

    /*
     * Responsável por conter o algoritmo de envio / validação
     * de dados. Algoritmo vinculado ao menos ao principal
     * botão em tela.
     * */
    /*
     * Método template.
     * Responsável por conter o algoritmo de envio / validação
     * de dados. Algoritmo vinculado ao menos ao principal
     * botão em tela.
     * */
    void mainAction() {
        blockFields(true);
        isMainButtonSending(true);
        showProxy(true);
        backEndFakeDelay();
    }

    /*
     * Método único.
     * */
    abstract public void backEndFakeDelay();

    /*
     * Necessário para que os campos de formulário não possam
     * ser acionados depois de enviados os dados.
     * */
    abstract public void blockFields(Boolean status);

    /*
     * Muda o rótulo do botão principal de acordo com o status
     * do envio de dados.
     * */
    abstract public void isMainButtonSending(Boolean status);


    /*
     * Fake method - Somente para testes temporários em atividades
     * e fragmentos que contêm formulários.
     * */
    protected void backEndFakeDelay(final Boolean statusAction, final String feedbackMessage) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                 * Simulando um delay de latência de
                 * 1 segundo.
                 * */
                SystemClock.sleep(1000);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        blockFields(false);
                        isMainButtonSending(false);
                        showProxy(false);
                        snackBarFeedback(containerForm, statusAction, feedbackMessage);
                    }
                });
            }
        }).start();

    }


    /*
     * Método responsável por invocar o Dialog de password antes
     * que o envio do formulário ocorra. Dialog necessário em
     * alguns formulários críticos onde parte da validação é a
     * verificação da senha.
     * */
    public void callPasswordDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        /*
         * Inflando o layout e configurando o AlertDialog. O
         * valor null está sendo colocado como segundo argumento
         * de inflate(), pois o layout parent do layout que
         * está sendo inflado será o layout nativo do dialog.
         * */
        builder
                .setView(inflater.inflate(R.layout.dialog_password, null))
                .setPositiveButton(
                        R.string.dialog_password_go,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mainAction();
                            }
                        }
                )
                .setNegativeButton(
                        R.string.dialog_password_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }
                )
                .setCancelable(false);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                /*
                 * É preciso colocar qualquer configuração
                 * extra das Views do Dialog dentro do
                 * listener de "dialog em apresentação",
                 * caso contrário uma NullPointerException
                 * será gerada, tendo em mente que é somente
                 * quando o "dialog está em apresentação"
                 * que as Views dele existem como objetos.
                 * */

                //mudando aqui pois os botoes esta sendo usados os padrões(o xml criado nao tem os botoes)

                dialog
                        .getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ColorUtils.getColor(R.color.colorText));

                dialog
                        .getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ColorUtils.getColor(R.color.colorText));

                EditText etPassword = dialog.findViewById(R.id.et_password);
                extension_functions.isValidPassword(etPassword, getString(R.string.invalid_password));
                etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        dialog.cancel();
                        mainAction();
                        return false;
                    }
                });
            }
        });


        dialog.show();
    }


    /*
     * Método necessário para atualizar o ViewGroup
     * fl_form, que é container dos layouts de formulários
     * carregados em fragment_form, deixando ele
     * pronto para receber uma lista de itens ou formulários
     * que têm os próprios padding e posicionamento.
     * */
    protected void updateFlFormToFullFreeScreen(){

        fl_form.setPadding( 0,0,0,0 );

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)fl_form.getLayoutParams();
        layoutParams.gravity = Gravity.NO_GRAVITY;
        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
    }

}
