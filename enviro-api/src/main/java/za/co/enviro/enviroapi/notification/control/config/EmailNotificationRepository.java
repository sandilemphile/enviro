package za.co.enviro.enviroapi.notification.control.config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.enviro.enviroapi.notification.entity.EmailNotificationEntity;

@Repository
public interface EmailNotificationRepository extends CrudRepository<EmailNotificationEntity, Long> {
}
