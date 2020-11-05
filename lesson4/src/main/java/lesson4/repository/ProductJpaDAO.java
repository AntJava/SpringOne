package lesson4.repository;

import lesson4.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaDAO extends JpaRepository<Product, Integer> {
    List<Product> findAllByTitleLike(String title);
    List<Product> findAllByIdBetween(Integer startId, Integer endId);
    List<Product> findAllByPriceBetween(Float startPrice, Float endPrice);
}
