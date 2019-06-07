package za.co.enviro.enviroapi.notification.control;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.enviro.enviroapi.notification.entity.EmailTemplateEntity;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends CrudRepository<EmailTemplateEntity, Long> {
    Optional<EmailTemplateEntity> findByName(String name);
}
