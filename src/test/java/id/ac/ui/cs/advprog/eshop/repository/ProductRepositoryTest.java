package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Clear repository before each test
        productRepository = new ProductRepository();
    }

    @Nested
    @DisplayName("Create Product Tests")
    class CreateProductTests {
        @Test
        void testCreateAndFind() {
            Product product = new Product();
            product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product.setProductName("Sampo Cap Bambang");
            product.setProductQuantity(100);
            productRepository.create(product);

            Iterator<Product> productIterator = productRepository.findAll();
            assertTrue(productIterator.hasNext());
            Product savedProduct = productIterator.next();
            assertEquals(product.getProductId(), savedProduct.getProductId());
            assertEquals(product.getProductName(), savedProduct.getProductName());
            assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        }

        @Test
        void testCreateAssignsUniqueId() {
            Product product = new Product();
            product.setProductName("Sample Product");
            product.setProductQuantity(1);
            
            Product created = productRepository.create(product);
            assertNotNull(created.getProductId());
            assertTrue(created.getProductId().length() > 0);
        }
    }

    @Nested
    @DisplayName("Find Product Tests")
    class FindProductTests {
        @Test
        void testFindAllIfEmpty() {
            Iterator<Product> productIterator = productRepository.findAll();
            assertFalse(productIterator.hasNext());
        }

        @Test
        void testFindAllIfMoreThanOneProduct() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            Iterator<Product> productIterator = productRepository.findAll();
            assertTrue(productIterator.hasNext());
            Product savedProduct = productIterator.next();
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            savedProduct = productIterator.next();
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertFalse(productIterator.hasNext());
        }

        @Test
        void testFindByIdReturnsCorrectProduct() {
            Product product = new Product();
            product.setProductName("Test Product");
            product.setProductQuantity(10);
            Product created = productRepository.create(product);

            Product found = productRepository.findById(created.getProductId());
            assertNotNull(found);
            assertEquals(created.getProductId(), found.getProductId());
        }

        @Test
        void testFindByIdWithNonexistentId() {
            Product found = productRepository.findById("nonexistent-id");
            assertNull(found);
        }
    }

    @Nested
    @DisplayName("Edit Product Tests")
    class EditProductTests {
        @Test
        void testEditExistingProduct() {
            // Test: edit an existing product
            Product product = new Product();
            product.setProductName("Brownies");
            product.setProductQuantity(100);
            Product createdProduct = productRepository.create(product);

            Product updatedProduct = new Product();
            updatedProduct.setProductId(createdProduct.getProductId());
            updatedProduct.setProductName("Cookies");
            updatedProduct.setProductQuantity(50);

            // Product details should be updated successfully

            Product result  = productRepository.edit(updatedProduct);
            assertNotNull(result);
            assertEquals(updatedProduct.getProductName(), result.getProductName());
            assertEquals(updatedProduct.getProductQuantity(), result.getProductQuantity());
        }

        @Test
        void testNonExistingProduct() {
            // Test: edit a non-existing product
            Product product = new Product();
            product.setProductId("eb558e9f-1c39-460e-8860-72af6af63bd6");
            product.setProductName("Sampo Cap Bambu");
            product.setProductQuantity(100);

            // Product details should not be updated
            Product result = productRepository.edit(product);
            assertNull(result);
        }
        
        @Test
        void testEditWithNullProductId() {
            Product product = new Product();
            product.setProductName("Test");
            product.setProductQuantity(1);
            assertNull(productRepository.edit(product));
        }

        @Test
        void testEditOnlyFirstProduct () {
            // Test: edit only one product out of multiple
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            Product updatedProduct = new Product();
            updatedProduct.setProductId(product1.getProductId());
            updatedProduct.setProductName("Sampo Cap Bambu");
            updatedProduct.setProductQuantity(200);

            // Product details should be updated successfully
            Product result = productRepository.edit(updatedProduct);
            assertNotNull(result);
            assertEquals(updatedProduct.getProductName(), result.getProductName());

            // Second product should be unchanged
            Product unchangedProduct = productRepository.findById(product2.getProductId());
            assertEquals(product2.getProductName(), unchangedProduct.getProductName());
        }
    }

    @Nested
    @DisplayName("Delete Product Tests")
    class DeleteProductTests {
        @Test
        void testDeleteExistingProduct() {
            // Test: delete an existing product
            Product product = new Product();
            product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product.setProductName("Sampo Cap Bambang");
            product.setProductQuantity(100);
            productRepository.create(product);

            // Product should be deleted successfully and repository should be empty
            boolean isDeleted = productRepository.delete(product.getProductId());
            assertTrue(isDeleted);
            Iterator<Product> productIterator = productRepository.findAll();
            assertFalse(productIterator.hasNext());
        }

        @Test
        void testDeleteNonExistentProduct() {
            boolean deleted = productRepository.delete("random-id");
            assertFalse(deleted);
        }

        @Test
        void testDeleteWithNullId() {
            assertFalse(productRepository.delete(null));
        }

        @Test
        void testDeleteWithEmptyId() {
            assertFalse(productRepository.delete(""));
        }

        @Test
        void voidDeleteFirstProductOutOfMultipleProducts() {
            // Test: remove first product from repository containing multiple
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            boolean deleted = productRepository.delete(product1.getProductId());
            assertTrue(deleted);

            // Check if only one product remains and the other is intact
            Iterator<Product> productIterator = productRepository.findAll();
            assertTrue(productIterator.hasNext());
            Product remainingProduct = productIterator.next();
            assertEquals(product2.getProductId(), remainingProduct.getProductId());
            assertFalse(productIterator.hasNext());
        }
    }

}
