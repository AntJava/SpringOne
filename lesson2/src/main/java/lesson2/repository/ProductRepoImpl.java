package lesson2.repository;

import org.springframework.stereotype.Repository;
import lesson2.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepoImpl {

    private Map<Integer, Product> repo = new HashMap<>();
    private int id = 0;

    {
        for (int i = 0; i < 6; i++) {
            repo.put(++id, new Product(id, "product" + id, (float)Math.random()*300));
        }
    }

    public Product getById(Integer id){
        return repo.get(id);
    }

    public List<Product> getAll(){
        return new ArrayList<>(repo.values());
    }

    public Product save(Product product){
        if(product.getId() == null){
            product.setId(++id);
        }
        repo.put(product.getId(), product);
        return product;
    }

}
