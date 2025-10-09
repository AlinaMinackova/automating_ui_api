package api.models.carts;


import api.models.products.ProductResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    private Integer id;
    private Integer userId;
    private String date;
    private ArrayList<ProductResponse> products;
    @JsonProperty("__v")
    private Integer v;
}


