package application.repositories;

import application.entities.Car;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findByType(String type);
    List<Car> findByPower(Integer power);
}
