package za.co.enviro.enviroapi.usermanagement.entity;

import lombok.Getter;
import lombok.Setter;
import za.co.enviro.enviroapi.accesscontrol.entity.BaseAuditEntity;
import za.co.enviro.enviroapi.usermanagement.entity.datatype.Gender;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ev_user")
public class UserEntity extends BaseAuditEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    private Integer age;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
