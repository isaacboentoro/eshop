package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class CarRepository {
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car){
        if(car.getCarId() == null){
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }
    public Iterator<Car> findAll(){
        return carData.iterator();
    }
    public Car findById(String id){
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public Car update(String id, Car updateData) {
        Car carToUpdate = findById(id);
        if (carToUpdate != null) {
            carToUpdate.setCarName(updateData.getCarName());
            carToUpdate.setCarColor(updateData.getCarColor());
            carToUpdate.setCarQuantity(updateData.getCarQuantity());
            return carToUpdate;
        }
        return null;
    }
    public void delete(String id) {carData.removeIf(car -> car.getCarId().equals(id));}
}