package br.com.bittencourt.boni.lucas.blueshoes.view;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bittencourt.boni.lucas.blueshoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_instagram;
    private TextView tv_instagram;

    private ImageView iv_facebook;
    private TextView tv_facebook;

    private ImageView iv_twitter;
    private TextView tv_twitter;

    private ImageView iv_youtube;
    private TextView tv_youtube;

    private ImageView iv_linkedin;
    private TextView tv_linkedin;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        iv_instagram = view.findViewById(R.id.iv_instagram);
        tv_instagram = view.findViewById(R.id.tv_instagram);

        iv_facebook = view.findViewById(R.id.iv_facebook);
        tv_facebook = view.findViewById(R.id.tv_facebook);

        iv_twitter = view.findViewById(R.id.iv_twitter);
        tv_twitter = view.findViewById(R.id.tv_twitter);

        iv_youtube = view.findViewById(R.id.iv_youtube);
        tv_youtube = view.findViewById(R.id.tv_youtube);

        iv_linkedin = view.findViewById(R.id.iv_linkedin);
        tv_linkedin = view.findViewById(R.id.tv_linkedin);

        iv_instagram.setOnClickListener(this);
        tv_instagram.setOnClickListener(this);

        iv_facebook.setOnClickListener(this);
        tv_facebook.setOnClickListener(this);

        iv_twitter.setOnClickListener(this);
        tv_twitter.setOnClickListener(this);

        iv_youtube.setOnClickListener(this);
        tv_youtube.setOnClickListener(this);

        iv_linkedin.setOnClickListener(this);
        tv_linkedin.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_instagram:
                openNetwork(
                        "com.instagram.android",
                        "http://instagram.com/_u/cbf_futebol",
                        "http://instagram.com/cbf_futebol"
                );
                break;
            case R.id.iv_instagram:
                openNetwork(
                        "com.instagram.android",
                        "http://instagram.com/_u/cbf_futebol",
                        "http://instagram.com/cbf_futebol"
                );
                break;
            case R.id.tv_facebook:
                openNetwork(
                        "com.facebook.katana",
                        "fb://facewebmodal/f?href=https://www.facebook.com/thiengoCalopsita",
                        "https://www.facebook.com/thiengoCalopsita"
                );
                break;
            case R.id.iv_facebook:
                openNetwork(
                        "com.facebook.katana",
                        "fb://facewebmodal/f?href=https://www.facebook.com/thiengoCalopsita",
                        "https://www.facebook.com/thiengoCalopsita"
                );
                break;
            case R.id.tv_twitter:
                openNetwork(
                        "com.twitter.android",
                        "https://twitter.com/thiengoCalops",
                        "https://twitter.com/thiengoCalops"
                );
                break;
            case R.id.iv_twitter:
                openNetwork(
                        "com.twitter.android",
                        "https://twitter.com/thiengoCalops",
                        "https://twitter.com/thiengoCalops"
                );
                break;
            case R.id.tv_youtube:
                openNetwork(
                        "com.google.android.youtube",
                        "https://www.youtube.com/user/thiengoCalopsita",
                        "https://www.youtube.com/user/thiengoCalopsita"
                );
                break;
            case R.id.iv_youtube:
                openNetwork(
                        "com.google.android.youtube",
                        "https://www.youtube.com/user/thiengoCalopsita",
                        "https://www.youtube.com/user/thiengoCalopsita"
                );
                break;
            default:
                openNetwork(
                        "com.linkedin.android",
                        "https://www.linkedin.com/in/vin%C3%ADcius-thiengo-5179b180/",
                        "https://www.linkedin.com/in/vin%C3%ADcius-thiengo-5179b180/"
                );
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).updateToolbarTitleInFragment( R.string.about_frag_title );
    }

    private void openNetwork(
            String appPackage,
            String appAddress,
            String webAddress) {

        Uri uri = Uri.parse(appAddress);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        intent.setPackage(appPackage);

        try {
            getActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            /*
             * Se não houver o aplicativo da rede
             * social acionada, então abra a página
             * no navegador padrão do aparelho, Web.
             * */
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress)));
        }
    }

}
