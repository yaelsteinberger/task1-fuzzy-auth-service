package local.auth.service.mockauthservice.repository;


import local.auth.service.mockauthservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

     User findUserByUserName(String userName);

     User findTopByOrderByIdDesc();
}
