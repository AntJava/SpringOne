package lesson2.contoller;

import lesson2.domain.Product;
import lesson2.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {this.productService = productService;}

    //http://localhost:8080/app/ - GET
    @GetMapping("/")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "list";
    }

    //http://localhost:8080/app/1 - GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model,@PathVariable("id") Integer id){
        Product byId = productService.getById(id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    //http://localhost:8080/app/new - GET
    @GetMapping("/new")
    public String getNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }

    //http://localhost:8080/app/new - POST
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addNewProduct(Product product){
        Product savedProduct = productService.save(product);
        return "redirect:/" + savedProduct.getId();
    }

}
