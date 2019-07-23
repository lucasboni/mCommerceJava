package br.com.bittencourt.boni.lucas.blueshoes.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.domain.CreditCard;

public class CreditCardsDataBase {
    static public List<CreditCard> getItems(){
        return new ArrayList<>(Arrays.asList(
                new CreditCard(
                        "6502",
                        "Visa",
                        "Tony Stark"
                ),
                new CreditCard(
                        "9270",
                        "Mastercard",
                        "Scarlett Johansson"
                ),
                new CreditCard(
                        "661",
                        "American Express",
                        "Margot Robbie"
                ),
                new CreditCard(
                        "8738",
                        "Visa",
                        "Vivian Hernandez"
                ),
                new CreditCard(
                        "9011",
                        "Visa",
                        "Andrew Jackson"
                )
        ));
    }
}
