package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("123");
        car.setCarName("Tesla");
        car.setCarColor("Red");
        car.setCarQuantity(1);
    }

    @Test
    void testCreateCar() {
        when(carRepository.create(car)).thenReturn(car);
        Car created = carService.create(car);
        verify(carRepository).create(car);
        assertEquals(car, created);
    }

    @Test
    void testFindAllCars() {
        List<Car> cars = Arrays.asList(car);
        when(carRepository.findAll()).thenReturn(cars.iterator());
        List<Car> found = carService.findAll();
        verify(carRepository).findAll();
        assertEquals(cars, found);
    }

    @Test
    void testFindById() {
        when(carRepository.findById("123")).thenReturn(car);
        Car found = carService.findById("123");
        verify(carRepository).findById("123");
        assertEquals(car, found);
    }

    @Test
    void testUpdateCar() {
        carService.update("123", car);
        verify(carRepository).update("123", car);
    }

    @Test
    void testDeleteCar() {
        carService.deleteCarById("123");
        verify(carRepository).delete("123");
    }
}
