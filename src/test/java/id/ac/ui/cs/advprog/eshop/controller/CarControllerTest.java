package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarServiceImpl carservice;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private CarController controller;

    @BeforeEach
    void setUp() {
        // Create controller with constructor injection
        controller = new CarController(productService);
        // Manually inject the mocked carservice
        ReflectionTestUtils.setField(controller, "carservice", carservice);
    }

    @Test
    void testCreateCarPage() {
        String viewName = controller.createCarPage(model);
        assertEquals("createCar", viewName);
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        car.setCarId("car-1");
        car.setCarName("Toyota");
        
        when(carservice.create(car)).thenReturn(car);
        
        String viewName = controller.createCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carservice).create(car);
    }

    @Test
    void testListCar() {
        List<Car> carList = new ArrayList<>();
        Car car = new Car();
        car.setCarId("car-1");
        carList.add(car);
        
        when(carservice.findAll()).thenReturn(carList);

        String viewName = controller.listCar(model);
        assertEquals("carList", viewName);
        verify(model).addAttribute("cars", carList);
    }

    @Test
    void testEditCarPage() {
        String carId = "car-1";
        Car car = new Car();
        car.setCarId(carId);
        
        when(carservice.findById(carId)).thenReturn(car);

        String viewName = controller.editCarPage(carId, model);
        assertEquals("editCar", viewName);
        verify(model).addAttribute("car", car);
        verify(carservice).findById(carId);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("car-1");
        car.setCarName("Updated Car");
        
        String viewName = controller.editCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carservice).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        String carId = "car-1";
        
        String viewName = controller.deleteCar(carId);
        assertEquals("redirect:listCar", viewName);
        verify(carservice).deleteCarById(carId);
    }
}