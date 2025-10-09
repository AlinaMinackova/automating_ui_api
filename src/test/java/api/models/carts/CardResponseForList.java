package api.models.carts;

import api.models.products.ProductResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseForList {
    private Integer id;
    private Integer userId;
    private String date;
    private ArrayList<Product> products;
    @JsonProperty("__v")
    private Integer v;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Product{
    private Integer productId;
    private Integer quantity;
}