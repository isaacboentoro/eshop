package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("Product 1");
        productList.add(product1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();
        assertEquals(2, allProducts.size());
        assertEquals("Product 1", allProducts.get(0).getProductName());
        assertEquals("Product 2", allProducts.get(1).getProductName());
        verify(productRepository).findAll();
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Original Name");

        when(productRepository.edit(product)).thenReturn(product);

        Product editedProduct = productService.edit(product);

        assertEquals(product, editedProduct);
        verify(productRepository).edit(product);
    }

    @Test
    void testFindProductById() {
        String productId = "456";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");

        when(productRepository.findById(productId)).thenReturn(product);

        Product foundProduct = productService.findById(productId);

        assertEquals(product, foundProduct);
        verify(productRepository).findById(productId);
    }

    @Test
    void testDeleteProduct() {
        String productId = "789";

        productService.delete(productId);

        verify(productRepository).delete(productId);
    }
}