package lesson5.controller;

import lesson5.domain.Product;
import lesson5.dto.EntityNotFoundResponse;
import lesson5.exeption.EntityNotFoundException;
import lesson5.dao.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products-rest")
public class ProductControllerRest {

    private final ProductDao productDao;

    public ProductControllerRest(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getAll(){
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id){
        checkById(id);
        return productDao.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        System.out.println(product);
        return productDao.save(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable(name = "productId") Integer id){
        product.setId(id);
        System.out.println(product);
        return productDao.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        checkById(id);
        productDao.deleteById(id);
    }

    private void checkById(@PathVariable Integer id) {
        if(!productDao.existsById(id)){
            throw new EntityNotFoundException("Product", id, "Product not found");
        }
    }

    @PostMapping("/filter")
    @ResponseBody
    public List<Product> filterByTitle(@RequestBody filterEntity filterEntity){
        System.out.println(filterEntity.id);
        List<Product> products = productDao.findAll().stream()
                .filter(product-> ( filterEntity.title.equals("") ? true : product.getTitle().contains(filterEntity.title)))
                .filter(product-> ( filterEntity.id.equals("") ? true : product.getId().toString().contains(filterEntity.id)))
                .filter(product-> (filterEntity.price.equals("") ? true : product.getPrice().toString().contains(filterEntity.price)))
                .collect(Collectors.toList());
        return products;
    }

    @ExceptionHandler
    public ResponseEntity<EntityNotFoundResponse> handleException(EntityNotFoundException ex){
        EntityNotFoundResponse response = new EntityNotFoundResponse();
        response.setEntityName(ex.getEntityName());
        response.setEntityId(ex.getEntityId());
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
