package br.com.bittencourt.boni.lucas.blueshoes.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.bittencourt.boni.lucas.blueshoes.domain.Brand;
import br.com.bittencourt.boni.lucas.blueshoes.domain.CreditCard;
import br.com.bittencourt.boni.lucas.blueshoes.domain.Price;
import br.com.bittencourt.boni.lucas.blueshoes.domain.Rate;
import br.com.bittencourt.boni.lucas.blueshoes.domain.Shoes;

public class AllShoesDataBase {

    static public List<Shoes> getItems() {
        return new ArrayList<>(Arrays.asList(
                new Shoes(
                        "Tênis VR Caminhada Confortável Detalhes Couro Masculino - Preto",
                        "https://static.netshoes.com.br/produtos/tenis-vr-caminhada-confortavel-detalhes-couro-masculino/06/E74-0413-006/E74-0413-006_zoom1.jpg",
                        new Brand(
                                "Adidas",
                                "https://cdn.awsli.com.br/400x300/1062/1062636/logo/1a09cccb3a.png"
                        ),
                        new Price(
                                119.90F,
                                10,
                                false,
                                0F
                        ),
                        new Rate(
                                3.5F,
                                193
                        )
                ),
                new Shoes(
                        "Chinelo Oakley Malibu Slide Masculino - Vermelho",
                        "https://static.netshoes.com.br/produtos/chinelo-oakley-malibu-slide-masculino/16/D63-5200-016/D63-5200-016_zoom1.jpg",
                        new Brand(
                                "Oakley",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Oakley_logo.svg/641px-Oakley_logo.svg.png"
                        ),
                        new Price(
                                149.99F,
                                2,
                                true,
                                84.99F
                        ),
                        new Rate(
                                4.5F,
                                37
                        )
                ),
                new Shoes(
                        "Chuteira Campo Nike Mercurial Superfly 6 Club CR7 MG - Branco e Preto",
                        "https://static.netshoes.com.br/produtos/chuteira-campo-nike-mercurial-superfly-6-club-cr7-mg/28/D12-9247-028/D12-9247-028_zoom1.jpg",
                        new Brand(
                                "Nike",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Logo_NIKE.svg/1200px-Logo_NIKE.svg.png"
                        ),
                        new Price(
                                349.99F,
                                5,
                                true,
                                229.99F
                        ),
                        new Rate(
                                4.5F,
                                6
                        )
                ),
                new Shoes(
                        "Tênis Olympikus Flower 415 Feminino - Rosa",
                        "https://static.netshoes.com.br/produtos/tenis-olympikus-flower-415-feminino/18/D22-1131-018/D22-1131-018_zoom1.jpg",
                        new Brand(
                                "Olympikus",
                                "https://logodownload.org/wp-content/uploads/2017/06/olympikus-logo.png"
                        ),
                        new Price(
                                159.99F,
                                3,
                                true,
                                119.99F
                        ),
                        new Rate(
                                4.5F,
                                339
                        )
                ),
                new Shoes(
                        "Tênis Nike Shox Nz Eu Masculino - Preto",
                        "https://static.netshoes.com.br/produtos/tenis-nike-shox-nz-eu-masculino/14/D12-9970-014/D12-9970-014_zoom1.jpg",
                        new Brand(
                                "Adidas",
                                "https://9d41bboy87-flywheel.netdna-ssl.com/outlets-at-legends/wp-content/uploads/sites/11/2019/07/Adidas_Logo.png"
                        ),
                        new Price(
                                699.99F,
                                10,
                                true,
                                439.99F
                        ),
                        new Rate(
                                4.5F,
                                820
                        )
                ),
                new Shoes(
                        "Tênis Nike Revolution 4 Feminino - Preto e Branco",
                        "https://static.netshoes.com.br/produtos/tenis-nike-revolution-4-feminino/26/D12-9120-026/D12-9120-026_zoom1.jpg",
                        new Brand(
                                "Nike",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Logo_NIKE.svg/1200px-Logo_NIKE.svg.png"
                        ),
                        new Price(
                                229.99F,
                                4,
                                true,
                                169.99F
                        ),
                        new Rate(
                                5F,
                                889
                        )
                ),
                new Shoes(
                        "Tênis Reebok Crossfit Nano 9 Masculino - Azul e Preto",
                        "https://static.netshoes.com.br/produtos/tenis-reebok-crossfit-nano-9-masculino/08/D19-3259-108/D19-3259-108_zoom1.jpg",
                        new Brand(
                                "Reebok",
                                "https://seeklogo.com/images/R/reebok-logo-B8CC638372-seeklogo.com.png"
                        ),
                        new Price(
                                599.99F,
                                10,
                                false,
                                0F
                        ),
                        new Rate(
                                4.5F,
                                13
                        )
                ),
                new Shoes(
                        "Tênis Nike Metcon Sport Masculino - Azul e amarelo",
                        "https://static.netshoes.com.br/produtos/tenis-nike-metcon-sport-masculino/76/HZM-1277-076/HZM-1277-076_zoom1.jpg",
                        new Brand(
                                "Nike",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Logo_NIKE.svg/1200px-Logo_NIKE.svg.png"
                        ),
                        new Price(
                                449.99F,
                                10,
                                false,
                                0F
                        ),
                        new Rate(
                                5F,
                                6
                        )
                )

        ));
    }
}
