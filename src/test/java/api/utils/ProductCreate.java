package api.utils;

import api.models.products.ProductRequest;
import api.models.products.Rate;

public class ProductCreate {
    public static ProductRequest randomProduct() {
        return ProductRequest.builder()
                .title("chocolate")
                .price(100.0)
                .description("very delicious")
                .category("candy")
                .image("http://example.com")
                .rating(new Rate(5.0, 10))
                .build();
    }
}
