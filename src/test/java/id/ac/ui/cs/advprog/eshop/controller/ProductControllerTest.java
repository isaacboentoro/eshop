package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @Test
    void testCreateProductPage() {
        String viewName = controller.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).create(product);
    }

    @Test
    void testListProduct() {
        List<Product> productList = new ArrayList<>();
        when(service.findAll()).thenReturn(productList);

        String viewName = controller.listProduct(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        String productId = "testId";
        Product product = new Product();
        when(service.findById(productId)).thenReturn(product);

        String viewName = controller.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
        verify(service).findById(productId);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String viewName = controller.editProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).edit(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "testId";
        String viewName = controller.deleteProduct(productId);
        assertEquals("redirect:/product/list", viewName);
        verify(service).delete(productId);
    }
}