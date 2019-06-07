package za.co.enviro.enviroapi.notification.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.notification.control.NotificationBa;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

@Component
public class NotificationBf implements NotificationBci {

    private NotificationBa notificationBa;

    @Autowired
    public NotificationBf(NotificationBa notificationBa) {
        this.notificationBa = notificationBa;
    }

    @Override
    public void sendEmail(UserEntity userEntity) {
        notificationBa.sendEmail(userEntity);
    }
}
