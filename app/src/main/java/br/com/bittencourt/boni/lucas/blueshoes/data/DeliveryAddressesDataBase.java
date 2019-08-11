package br.com.bittencourt.boni.lucas.blueshoes.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.domain.CreditCard;
import br.com.bittencourt.boni.lucas.blueshoes.domain.DeliveryAddress;

public class DeliveryAddressesDataBase {
    static public List<DeliveryAddress> getItems() {
        return new ArrayList<>(Arrays.asList(
                new DeliveryAddress(
                        "Rua das Oliveiras",
                        1366,
                        "Condomínio Aldeias",
                        "29154-630",
                        "Colina de Laranjeiras",
                        "Serra",
                        7
                ),
                new DeliveryAddress(
                        "Av. Jayme Clayton",
                        856,
                        "Alphaville",
                        "22598-611",
                        "Limeira",
                        "Tataupé",
                        24
                ),
                new DeliveryAddress(
                        "Rua Almeida Presidente",
                        2563,
                        "Happy Days",
                        "25668-178",
                        "Limeira",
                        "Sobral",
                        5
                ),
                new DeliveryAddress(
                        "Rua das Emas",
                        58,
                        "Ao lado do Hospital Jorge Santos",
                        "23665-558",
                        "Setor Segundo",
                        "Itajaí",
                        23
                )
        ));
    }
}
