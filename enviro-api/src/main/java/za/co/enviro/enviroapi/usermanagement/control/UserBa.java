package za.co.enviro.enviroapi.usermanagement.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.accesscontrol.control.exceptions.SystemException;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import java.util.Optional;

@Component
public class UserBa {

    private UserRepository userRepository;

    @Autowired
    public UserBa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userAlreadyExist(userEntity.getEmail())) {
            throw new SystemException("User with the email already exists");
        }
        return userRepository.save(userEntity);
    }

    private boolean userAlreadyExist(String email) {
        return findUserByEmail(email)
                .isPresent();
    }
}
