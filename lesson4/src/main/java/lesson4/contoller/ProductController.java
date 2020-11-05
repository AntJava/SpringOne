package lesson4.contoller;

import lesson4.domain.Product;
import lesson4.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {this.productService = productService;}

    //http://localhost:8080/app/ - GET
    @GetMapping("/")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list";
    }

    //http://localhost:8080/app/1 - GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model,@PathVariable("id") Integer id){
        Product byId = productService.findById(id);
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
        productService.saveAndSet(product);
        return "redirect:/";
    }

    //http://localhost:8080/app/new - GET
    @GetMapping("/newTest")
    public String addNewTestProduct(){
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setTitle("Product " + (i + 1));
            product.setPrice(5 + i*1.5f);
            productService.saveAndSet(product);
        }
        return "redirect:/";
    }

    //http://localhost:8080/app/price?priceFrom=5&priceTo=12 - GET
    @GetMapping("/price")
    public String listPrice(Model model,
                            @RequestParam(name = "priceFrom") float priceFrom,
                            @RequestParam(name = "priceTo") float priceTo){
        List<Product> products = productService.findAllByPriceBetween(priceFrom, priceTo);
        model.addAttribute("products", products);
        return "list";
    }





}
