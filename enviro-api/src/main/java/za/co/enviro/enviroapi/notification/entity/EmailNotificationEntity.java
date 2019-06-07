package za.co.enviro.enviroapi.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.enviro.enviroapi.accesscontrol.entity.BaseEntity;
import za.co.enviro.enviroapi.notification.entity.datatype.Status;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ev_email_notification")
public class EmailNotificationEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "sent_to", referencedColumnName = "id")
    private UserEntity sentTo;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "id")
    private EmailTemplateEntity email;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(name = "creation_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    @PrePersist
    protected void onPrePersist() {
        creationTimestamp = new Date();
    }

}
