package emt.proekt.bicycleshop.user.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Role;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
public class User extends AbstractEntity<UserId> {

    @Version
    private Long version;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private User() {

    }

    public User(@NonNull String username, @NonNull String password, @NonNull Role role) {
        super(DomainObjectId.randomId(UserId.class));
        setUsername(username);
        setPassword(password);
        setRole(role);
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public UserId id() {
        return null;
    }
}
