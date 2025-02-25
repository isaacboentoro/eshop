package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarTest {
    
    private Car car;
    
    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void testGetAndSetCarId() {
        String carId = "CAR-001";
        car.setCarId(carId);
        assertEquals(carId, car.getCarId());
    }

    @Test
    void testGetAndSetCarName() {
        String carName = "Toyota Camry";
        car.setCarName(carName); 
        assertEquals(carName, car.getCarName());
    }

    @Test
    void testGetAndSetCarColor() {
        String carColor = "Red";
        car.setCarColor(carColor);
        assertEquals(carColor, car.getCarColor());
    }

    @Test
    void testGetAndSetCarQuantity() {
        int quantity = 5;
        car.setCarQuantity(quantity);
        assertEquals(quantity, car.getCarQuantity());
    }

    @Test
    void testDefaultValues() {
        assertNull(car.getCarId());
        assertNull(car.getCarName());
        assertNull(car.getCarColor());
        assertEquals(0, car.getCarQuantity());
    }
}
