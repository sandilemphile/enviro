package za.co.enviro.enviroapi.accesscontrol.boundary;

import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

/**
 * Business component interface
 */
public interface AccessControlBci {
    UserEntity registerUser(UserEntity userEntity);
}
