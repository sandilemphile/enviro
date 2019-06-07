package za.co.enviro.enviroapi.notification.boundary;

import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

public interface NotificationBci {
    void sendEmail(UserEntity userEntity);
}
