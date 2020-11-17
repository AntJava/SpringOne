package lesson5.controller;

import lesson5.domain.Product;
import lesson5.service.ProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {this.productService = productService;}

    //http://localhost:8080/app/ - GET
    @GetMapping("/")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        return "redirect:/page";
    }

    //http://localhost:8080/app/page?size=5&page=1&priceFrom=1.0&priceTo=15.0 - GET
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(Model model,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size,
                       @RequestParam("priceFrom") Optional<Float> priceFrom,
                       @RequestParam("priceTo") Optional<Float> priceTo){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        float startPrice = priceFrom.orElse(0f);
        float endPrice = priceTo.orElse(99999f);
        Page<Product> products = productService.findPage(currentPage - 1, pageSize, startPrice, endPrice);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("products", products);
        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list";
    }

    //http://localhost:8080/app/1 - GET
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getById(Model model,@PathVariable("id") Integer id){
        Product byId = productService.findById(id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    //http://localhost:8080/app/1 - POST
    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    public String updateProduct(Product product){
        productService.saveAndSet(product);
        return "redirect:/product/{id}";
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
}
