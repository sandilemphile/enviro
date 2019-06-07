package za.co.enviro.enviroapi.accesscontrol.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.notification.boundary.NotificationBci;
import za.co.enviro.enviroapi.usermanagement.boundary.UserManagementBci;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

/**
 * Business Adapter
 */
@Component
public class AccessControlBa {

    private UserManagementBci userManagementBci;
    private NotificationBci notificationBci;

    @Autowired
    AccessControlBa(UserManagementBci userManagementBci, NotificationBci notificationBci) {
        this.userManagementBci = userManagementBci;
        this.notificationBci = notificationBci;
    }

    public UserEntity registerUser(UserEntity userEntity) {
        UserEntity user = userManagementBci.createUser(userEntity);
        new Thread(() -> {
            notificationBci.sendEmail(user);
        }).start();
        return user;
    }
}
