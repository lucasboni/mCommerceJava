package br.com.bittencourt.boni.lucas.blueshoes.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.bittencourt.boni.lucas.blueshoes.R;


public class ContactFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_phone_cities;
    private TextView tv_phone_cities;
    private ImageView iv_phone_other_regions;
    private TextView tv_phone_other_regions;

    private ImageView iv_email_orders;
    private TextView tv_email_orders;
    private ImageView iv_email_attendance;
    private TextView tv_email_attendance;

    private ImageView iv_address;
    private TextView tv_address;
    private TextView tv_info_block;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_phone_cities = view.findViewById(R.id.iv_phone_cities);
        tv_phone_cities = view.findViewById(R.id.tv_phone_cities);
        iv_phone_other_regions = view.findViewById(R.id.iv_phone_other_regions);
        tv_phone_other_regions = view.findViewById(R.id.tv_phone_other_regions);

        iv_email_orders = view.findViewById(R.id.iv_email_orders);
        tv_email_orders = view.findViewById(R.id.tv_email_orders);
        iv_email_attendance = view.findViewById(R.id.iv_email_attendance);
        tv_email_attendance = view.findViewById(R.id.tv_email_attendance);

        iv_address = view.findViewById(R.id.iv_address);
        tv_address = view.findViewById(R.id.tv_address);

        tv_info_block =view.findViewById(R.id.tv_info_block);

        iv_phone_cities.setOnClickListener(this);
        tv_phone_cities.setOnClickListener(this);
        iv_phone_other_regions.setOnClickListener(this);
        tv_phone_other_regions.setOnClickListener(this);

        iv_email_orders.setOnClickListener(this);
        tv_email_orders.setOnClickListener(this);
        iv_email_attendance.setOnClickListener(this);
        tv_email_attendance.setOnClickListener(this);

        iv_address.setOnClickListener(this);
        tv_address.setOnClickListener(this);

        tv_info_block.setText(getString( R.string.contact_frag_info));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).updateToolbarTitleInFragment( R.string.contact_frag_title );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_phone_cities:
                /*
                 * O número de telefone está sendo concatenado com o
                 * "0", pois como se trata de um número fixo local é
                 * aguardado, em discagem, o DDD da região logo
                 * depois de um "0".
                 * */
                phoneCallIntent("0" + tv_phone_cities.getText());
                break;
            case R.id.tv_phone_cities:
                /*
                 * O número de telefone está sendo concatenado com o
                 * "0", pois como se trata de um número fixo local é
                 * aguardado, em discagem, o DDD da região logo
                 * depois de um "0".
                 * */
                phoneCallIntent("0" + tv_phone_cities.getText());
                break;
            case R.id.iv_phone_other_regions:
                phoneCallIntent(tv_phone_other_regions.getText().toString());
                break;
            case R.id.tv_phone_other_regions:
                phoneCallIntent(tv_phone_other_regions.getText().toString());
                break;
            case R.id.iv_email_orders:
                mailToIntent(tv_email_orders.getText().toString());
                break;
            case R.id.tv_email_orders:
                mailToIntent(tv_email_orders.getText().toString());
                break;
            case R.id.iv_email_attendance:
                mailToIntent(tv_email_orders.getText().toString());
                break;
            case R.id.tv_email_attendance:
                mailToIntent(tv_email_orders.getText().toString());
                break;
            case R.id.iv_address:
                mapsRouteIntent(getString( R.string.contact_frag_address_formatted_to_google_maps ));
                break;
            case R.id.tv_address:
                mapsRouteIntent(getString( R.string.contact_frag_address_formatted_to_google_maps ));
                break;
        }
    }


    private void phoneCallIntent(String number) {
        /*
         * O replace() está sendo utilizado para remover
         * caracteres que não são aceitos no Intent de
         * data "tel:"
         * */
        String phoneNumber = number.replace("(\\s|\\)|\\(|-)", "");
        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse("tel:" + "phoneNumber"));

        /*
         * Aqui não há necessidade de um try{}catch{} para o
         * startActivity(), pois é improvável que o aplicativo de
         * telefonema não esteja presente no smartphone ou tablet
         * Android.
         * */
        getActivity().startActivity(intent);
    }

    private void mailToIntent(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        try {
            Intent intentChooser = Intent.createChooser(intent, getString(R.string.chooser_email_text));//selecionad o email
            getActivity().startActivity(intentChooser);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), getString(R.string.info_email_app_install), Toast.LENGTH_LONG).show();
        }
    }

    private void mapsRouteIntent(String address) {
        String location = Uri.encode(address);
        String navigation = "google.navigation:q=" + location;

        Uri navigationUri = Uri.parse(navigation);
        Intent intent = new Intent(Intent.ACTION_VIEW, navigationUri);

        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {//verifica se esta instalado o maps
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), getString(R.string.info_google_maps_install), Toast.LENGTH_LONG).show();
        }
    }
}
