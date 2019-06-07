package za.co.enviro.enviroapi.accesscontrol.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseAuditEntity extends BaseEntity {

    @NotNull
    @Column(name = "creation_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    @NotNull
    @Column(name = "modification_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTimestamp;

    @PrePersist
    protected void onPrePersist() {
        Date date = new Date();
        creationTimestamp = date;
        modificationTimestamp = date;
    }

    @PreUpdate
    private void onPreUpdate() {
        modificationTimestamp = new Date();
    }
}
