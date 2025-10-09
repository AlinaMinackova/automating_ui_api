package api.models.carts;

import api.models.products.ProductRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    public Integer userId;
    private String date;
    public List<ProductRequest> products;
    @JsonProperty("__v")
    private Integer v;
}
