package com.sarigul.sisal.restApi;

import com.sarigul.sisal.entity.Product;
import com.sarigul.sisal.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        return service.save(product);
    }

    @GetMapping
    public List<Product> getAll(){
        return service.getAll();
    }

    @PutMapping
    public Product update(@RequestBody Product product) throws Exception {
        return service.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        service.deleteById(id);
    }
}
