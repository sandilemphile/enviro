package za.co.enviro.enviroapi.accesscontrol.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.accesscontrol.control.AccessControlBa;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

/**
 * Business facade for access control.
 */
@Component
public class AccessControlBf implements AccessControlBci {

    private AccessControlBa accessControlBa;

    @Autowired
    public AccessControlBf(AccessControlBa accessControlBa) {
        this.accessControlBa = accessControlBa;
    }

    @Override
    public UserEntity registerUser(UserEntity userEntity) {
        return accessControlBa.registerUser(userEntity);
    }
}
