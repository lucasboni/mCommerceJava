package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.Annotation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


import br.com.bittencourt.boni.lucas.blueshoes.R;

public class PrivacyPolicyFragment extends Fragment {


    private TextView tv_privacy_policy_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(
                        R.layout.fragment_privacy_policy,
                        container,
                        false
                );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        tv_privacy_policy_content = getView().findViewById(R.id.tv_privacy_policy_content);

        /*
         * Vamos manter a invocação do método privacyPolicyLoad()
         * em um método que vem depois do método onCreateActivity()
         * para assim podermos manter a sintaxe
         * kotlin-android-extensions de acesso às Views do layout
         * do fragmento.
         * */
        privacyPolicyLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).updateToolbarTitleInFragment( R.string.privacy_policy_frag_title );
    }

    private void privacyPolicyLoad(){

        /*
         * Aplicando o casting de CharSequence para SpannedString
         * para que seja possível acessar as Spans presentes no
         * texto.
         *
         * É preciso utilizar getText(), que retorna um CharSequence.
         * getString() retorna uma String e Strings não contém Spans.
         * */
        SpannedString text = new SpannedString(getText( R.string.privacy_policy_content));

        /*
         * Obtendo todas as Annotation Span do texto.
         * */
        Annotation[] annotations = text.getSpans(
                0, /* Posição inicial do texto. */
                text.length(), /* Posição final do texto. */
                Annotation.class /* Classe Span (ParcelableSpan) para recolher objetos do tipo. */
        );

        /*
         * Criando uma cópia do texto, com SpannableString,
         * para que seja possível adicionar ou remover Span além
         * de também ser possível adicionar ou remover caracteres.
         * */
        SpannableString spannedText = new SpannableString( text );

        /*
         * Iterando sobre todas as annotations encontradas.
         * */
        for( Annotation annotation :annotations ){

            int textStartPos = text.getSpanStart( annotation );
            int textEndPos = text.getSpanEnd( annotation );
            int spanFlag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

            if( annotation.getKey().equals("title") ){ /* Títulos. */
                /*
                 * Todos os títulos têm uma fonte customizada
                 * aplicada a eles, mais precisamente a fonte
                 * Pathway Gothic One.
                 * */
                Typeface typeFace = ResourcesCompat
                        .getFont(
                                getActivity(),
                        R.font.pathway_gothic_one_regular
                    );
                spannedText.setSpan(
                        new RelativeSizeSpan(2f),
                textStartPos,
                        textEndPos,
                        spanFlag
                );

                /*
                 * Todos os títulos têm o estilo negrito aplicado a
                 * eles.
                 * */
                spannedText.setSpan(
                        new StyleSpan( Typeface.BOLD ),
                        textStartPos,
                        textEndPos,
                        spanFlag
                );

                /*
                 * Obtendo o tamanho correto do texto de acordo com a
                 * AnnotationSpan de título presente nele.
                 * */
                RelativeSizeSpan textSizeSpan;
                if(annotation.getValue().equals("main") ){
                    textSizeSpan = new RelativeSizeSpan( 1.5F );
                } else if(annotation.getValue().equals("sub") ){
                    textSizeSpan = new RelativeSizeSpan( 1.3F );
                }else{
                    textSizeSpan = new RelativeSizeSpan( 1.1F );
                }
                spannedText.setSpan(
                        textSizeSpan,
                        textStartPos,
                        textEndPos,
                        spanFlag
                );
            }
            else if( annotation.getKey().equals("link") ){ /* Links. */

                /*
                 * Os "+ 1" e "- 1" sendo utilizados é
                 * para evitar que trechos que não fazem
                 * parte do texto de link sejam
                 * adicionados ao link.
                 * */
                spannedText.setSpan(
                        new URLSpan( annotation.getValue() ),
                        textStartPos + 1,
                        textEndPos - 1,
                        spanFlag
                );

                /*
                 * Colocando a cor de link utilizado
                 * neste projeto Android, pois este é
                 * um trecho de âncora dentro das
                 * políticas de privacidade.
                 * */
                spannedText.setSpan(
                        new ForegroundColorSpan(
                                ContextCompat
                                        .getColor(
                                                getActivity(),
                        R.color.colorLink
                            )
                    ),
                textStartPos + 1,
                        textEndPos - 1,
                        spanFlag
                );
            }
        }

        /*
         * Para que o trecho de texto com Span de link externo
         * possa ser clicável. É preciso configurar um
         * LinkMovementMethod.
         * */
        tv_privacy_policy_content.setMovementMethod(LinkMovementMethod.getInstance());

        /*
         * Colocando o texto estilizado no TextView de
         * Políticas de Privacidade.
         *
         * A invocação do método trimStart() a seguir se
         * faz necessária como uma estratégia para remover
         * o espaço em branco do início do texto de
         * Políticas de Privacidade no arquivo XML.
         * */
        tv_privacy_policy_content.setText(spannedText);
    }
}
