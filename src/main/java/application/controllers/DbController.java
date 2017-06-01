package application.controllers;

import application.repositories.UserRepository;
import application.entities.UserAccount;
import application.repositories.CarRepository;
import application.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/db")
public class DbController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarRepository carRepository;

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        UserAccount n = new UserAccount();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @RequestMapping(value = "/addUserCars", method = RequestMethod.POST)
    public @ResponseBody String addUserCars(@RequestParam String name, @RequestParam String email, @RequestBody Car car ) {
        UserAccount n = new UserAccount();
        n.setName(name);
        n.setEmail(email);
        List<Car> cars = new ArrayList();
        car.setUserAccount(n);
        cars.add(car);
        n.setCarList(cars);
        userRepository.save(n);
        return "Saved";
    }
    
    @RequestMapping(value = "/addCarForUser/{id}", method = RequestMethod.POST)
    public @ResponseBody String addCarForUser(@PathVariable Integer id, @RequestBody Car car ) {
        UserAccount n = userRepository.findOne(id);
        car.setUserAccount(n);
        carRepository.save(car);
        return "Saved";
    }

    @Transactional
    @GetMapping(path = "/new")
    public @ResponseBody UserAccount newUser(@RequestParam String name, @RequestParam String email) {
        UserAccount u = new UserAccount();
        u.setName(name);
        u.setEmail(email);
        em.persist(u);
        return u;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/see")
    public @ResponseBody Object getById(@RequestParam int id) {
        Query q = em.createNativeQuery("SELECT u.name FROM user u WHERE u.id = ?");
        q.setParameter(1, id);
        List<Object[]> users = q.getResultList();
        UserAccount user = em.find(UserAccount.class, id);
        return users;
    }
}
