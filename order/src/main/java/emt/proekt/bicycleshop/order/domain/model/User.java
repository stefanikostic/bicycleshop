package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.Role;
import lombok.Getter;

@Getter
public class User {

    private String username;

    private String password;

    private Role role;

}
