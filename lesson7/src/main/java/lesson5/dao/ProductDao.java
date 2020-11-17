package lesson5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import lesson5.domain.Product;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetween(Double startPrice, Double endPrice);
}
