package za.co.enviro.enviroapi.usermanagement.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.usermanagement.control.UserBa;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import java.util.Optional;

/**
 * Business facade
 */
@Component
public class UserManagementBf implements UserManagementBci {

    private UserBa userBa;

    @Autowired
    public UserManagementBf(UserBa userBa) {
        this.userBa = userBa;
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userBa.findUserByEmail(email);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userBa.createUser(userEntity);
    }
}
