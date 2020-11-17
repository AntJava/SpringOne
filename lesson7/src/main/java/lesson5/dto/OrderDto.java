package lesson5.dto;

import java.util.List;

public class OrderDto {

    private Integer id;
    private List<ProductDto> orderedProducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<ProductDto> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
