package hello;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Integer> {

}
