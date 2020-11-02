package lesson2.service;

import org.springframework.stereotype.Service;
import lesson2.domain.Product;
import lesson2.repository.ProductRepoImpl;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl {

    private ProductRepoImpl productRepo;

    public ProductServiceImpl(ProductRepoImpl productRepo) {
        this.productRepo = productRepo;
    }

    public Product getById(Integer id){
        return productRepo.getById(id);
    }

    public List<Product> getAll(){
        List<Product> products = productRepo.getAll();
        products.sort(Comparator.comparingInt(Product::getId));
        return products;
    }

    public Product save(Product product){
        return productRepo.save(product);
    }

}
