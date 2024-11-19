import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Create a product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
    	if(isAdmin()) {
        return productRepository.save(product);}
    	else {throw new AccessDeniedException("vous devrez etre admin pour se connecter")}
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
    	if(isAdmin()) {
        return productRepository.findAll();}
    	else {throw new AccessDeniedException("vous devrez etre admin pour se connecter")}
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
    	if(isAdmin()) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    	

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);}
    	else {throw new AccessDeniedException("vous devrez etre admin pour se connecter")}
    }
    //Remove a product 
    @DeletMapping("/{id}")
    Public Product deleteProduct(@PathVariable Long id) {
    	if(isAdmin()) {
    	ProductService.deleteProduct(id);
    }
    	else {throw new AccessDeniedException("vous devrez etre admin pour se connecter")}
    }
}
