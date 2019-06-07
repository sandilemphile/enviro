package za.co.enviro.enviroapi.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.enviro.enviroapi.accesscontrol.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ev_email_template")
public class EmailTemplateEntity extends BaseEntity {
    @Column(name = "content")
    private String content;

    @Column(name ="name")
    private String name;
}
