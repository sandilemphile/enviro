package za.co.enviro.enviroapi.usermanagement.control.dto;

import lombok.Getter;
import lombok.Setter;
import za.co.enviro.enviroapi.accesscontrol.entity.BaseAuditEntity;
import za.co.enviro.enviroapi.usermanagement.entity.datatype.Gender;

@Getter
@Setter
public class UserDto extends BaseAuditEntity {
    private String fullName;
    private String email;
    private Integer age;
    private String idNumber;
    private Gender gender;
}
