package za.co.enviro.enviroapi.usermanagement.control;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
