package br.com.bittencourt.boni.lucas.blueshoes.view.shoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.R;
import br.com.bittencourt.boni.lucas.blueshoes.domain.Rate;
import br.com.bittencourt.boni.lucas.blueshoes.domain.Shoes;

public class AllShoesListAdapter extends  RecyclerView.Adapter<AllShoesListAdapter.ViewHolder>{


    private List<Shoes> shoesList;

    public AllShoesListAdapter(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater
                .from( parent.getContext() )
                .inflate(
                        R.layout.shoes_item,
                        parent,
                        false
                );

        return new ViewHolder( layout );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData( shoesList.get(position));
    }

    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private Context context;

        private ImageView ivModel;
        private TextView tvModel;

        private ImageView ivBrand;

        private LinearLayout llDiscount;
        private TextView tvDiscount;

        private TextView tvPriceCurrent;
        private TextView tvPriceWithoutDiscount;
        private TextView tvPriceParcels;

        private ImageView ivRateStar1;
        private ImageView ivRateStar2;
        private ImageView ivRateStar3;
        private ImageView ivRateStar4;
        private ImageView ivRateStar5;
        private TextView tvNumRates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            ivModel = itemView.findViewById( R.id.iv_model );
            tvModel = itemView.findViewById( R.id.tv_model );

            ivBrand = itemView.findViewById( R.id.iv_brand );

            llDiscount = itemView.findViewById( R.id.ll_discount );
            tvDiscount = itemView.findViewById( R.id.tv_discount );
            tvPriceCurrent = itemView.findViewById( R.id.tv_price_current );
            tvPriceWithoutDiscount = itemView.findViewById( R.id.tv_price_without_discount );
            tvPriceParcels = itemView.findViewById( R.id.tv_price_parcels );

            ivRateStar1 = itemView.findViewById( R.id.iv_rate_star_1 );
            ivRateStar2 = itemView.findViewById( R.id.iv_rate_star_2 );
            ivRateStar3 = itemView.findViewById( R.id.iv_rate_star_3 );
            ivRateStar4 = itemView.findViewById( R.id.iv_rate_star_4 );
            ivRateStar5 = itemView.findViewById( R.id.iv_rate_star_5 );

            tvNumRates = itemView.findViewById( R.id.tv_num_rates );
        }

        public void setData(Shoes shoes) {

            Picasso.get()
                    .load( shoes.getMainImg() )
                    .into( ivModel );
            ivModel.setContentDescription(shoes.getModel());
            tvModel.setText(shoes.getModel());

            Picasso.get()
                    .load( shoes.getBrand().getLogo() )
                    .into( ivBrand );
            ivBrand.setContentDescription(shoes.getBrand().getLabel());
            setPrice( shoes );
            setRate( shoes.getRate() );
        }

        private void setPrice(Shoes shoes){

            if( shoes.getPrice().isHasDiscount()){
                llDiscount.setVisibility(View.VISIBLE);
                tvPriceWithoutDiscount.setVisibility(View.VISIBLE);

                tvDiscount.setText(shoes.getPrice().getPercentDiscountLabel());
                tvPriceCurrent.setText(shoes.getPrice().getWithDiscountLabel( context ));
                tvPriceWithoutDiscount.setText(shoes.getPrice().getNormalLabel( context ));
            }
            else{
                llDiscount.setVisibility(View.GONE);
                tvPriceWithoutDiscount.setVisibility(View.GONE);

                tvPriceCurrent.setText(shoes.getPrice().getNormalLabel( context ));
            }

            tvPriceParcels.setText(shoes.getPrice().getParcelsLabel( context ));
        }

        private void setRate(Rate rate){

            tvNumRates.setText(rate.getNumCommentsLabel());

            ivRateStar1.setImageResource( rate.getStarResource( 1 ) );
            ivRateStar2.setImageResource( rate.getStarResource( 2 ) );
            ivRateStar3.setImageResource( rate.getStarResource( 3 ) );
            ivRateStar4.setImageResource( rate.getStarResource( 4 ) );
            ivRateStar5.setImageResource( rate.getStarResource( 5 ) );
        }




    }
}
