package za.co.enviro.enviroapi.accesscontrol.boundary;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.enviro.enviroapi.usermanagement.control.dto.UserDto;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(path = "/api/v1/access-control")
public class AccessControlRest {

    private AccessControlBci accessControlBci;

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @Autowired
    public AccessControlRest(AccessControlBci accessControlBci) {
        this.accessControlBci = accessControlBci;
    }

    @PermitAll
    @PostMapping(path = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto registerUser(@RequestBody UserDto newUser) {
        UserEntity user = mapper.map(newUser, UserEntity.class);
        return mapper.map(accessControlBci.registerUser(user), UserDto.class);
    }
}
