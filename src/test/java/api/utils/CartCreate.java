package api.utils;

import api.models.carts.CardRequest;
import api.models.carts.CardResponse;

import java.util.List;

public class CartCreate {

    public static CardRequest createRandom(){
        return CardRequest.builder()
                .userId(1)
                .date("2020-03-01T00:00:00.000Z")
                .products(List.of(ProductCreate.randomProduct()))
                .v(0)
                .build();
    }
}
