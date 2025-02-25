package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    CarRepository carRepository;
    
    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarName("Civic");
        car.setCarColor("Red");
        car.setCarQuantity(1);
        carRepository.create(car);
        
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertNotNull(savedCar.getCarId());
    }

    @Test 
    void testFindById() {
        Car car = new Car();
        car.setCarName("Jazz");
        car.setCarColor("Blue"); 
        car.setCarQuantity(2);
        Car savedCar = carRepository.create(car);

        Car foundCar = carRepository.findById(savedCar.getCarId());
        assertNotNull(foundCar);
        assertEquals(savedCar.getCarId(), foundCar.getCarId());
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarName("Yaris");
        car.setCarColor("White");
        car.setCarQuantity(1);
        Car savedCar = carRepository.create(car);

        Car updateData = new Car();
        updateData.setCarName("Updated Yaris");
        updateData.setCarColor("Black");
        updateData.setCarQuantity(2);

        Car updatedCar = carRepository.update(savedCar.getCarId(), updateData);
        assertNotNull(updatedCar);
        assertEquals("Updated Yaris", updatedCar.getCarName());
        assertEquals("Black", updatedCar.getCarColor());
        assertEquals(2, updatedCar.getCarQuantity());
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarName("Accord");
        car.setCarColor("Silver");
        car.setCarQuantity(1);
        Car savedCar = carRepository.create(car);

        carRepository.delete(savedCar.getCarId());
        assertNull(carRepository.findById(savedCar.getCarId()));
    }
}
