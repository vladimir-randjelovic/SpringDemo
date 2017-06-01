package application.repositories;

import application.entities.UserAccount;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Integer> {
    List<UserAccount> findByName(String name);
    List<UserAccount> findByEmail(String email);
}
