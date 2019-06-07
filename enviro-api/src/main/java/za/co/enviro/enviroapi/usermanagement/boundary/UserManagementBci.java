package za.co.enviro.enviroapi.usermanagement.boundary;

import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import java.util.Optional;

/**
 * Business component interface
 */
public interface UserManagementBci {
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity createUser(UserEntity userEntity);
}
