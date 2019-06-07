package za.co.enviro.enviroapi.accesscontrol.control.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseAuditDto extends BaseDto {
    private Date creationTimestamp;
    private Date modificationTimestamp;
}
