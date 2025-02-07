package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(String.valueOf(productData.size() + 1));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        // Uses a Stream to filter the product by ID and returns the first element.
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product edit(Product updatedProduct) {
        // Finds the existing product by ID and replaces it with the updated product, returns null if product is not found.
        Product existingProduct = findById(updatedProduct.getProductId());
        if (existingProduct != null) {
            int index = productData.indexOf(existingProduct);
            productData.set(index, updatedProduct);
            return updatedProduct;
        }
        return null;
    }

    public boolean delete(String productId) {
        // removes the product by ID and returns true if the product is removed.
        return productData.removeIf(product -> 
            product.getProductId().equals(productId)
        );
    }
}