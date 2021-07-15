package switchtwentytwenty.project.authentication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Code adapted from https://www.javainuse.com/spring/boot-jwt and https://www.javainuse.com/spring/boot-jwt-mysql

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
