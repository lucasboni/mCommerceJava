package br.com.bittencourt.boni.lucas.blueshoes.domain;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class Rate {
    private float stars;
    private int numComments;


    public Rate(float stars, int numComments) {
        this.stars = stars;
        this.numComments = numComments;
    }




    public String getNumCommentsLabel() {
        return String.format(
                "(%d)",
                numComments
        );
    }


    public int getStarResource(int starPosition) {
        if (starPosition <= Math.floor(starPosition))
            return R.drawable.ic_star_filled;
        else if (starPosition == Math.ceil(starPosition))
            return R.drawable.ic_star_half_empty;
        else
            return R.drawable.ic_star_empty;
    }
}
