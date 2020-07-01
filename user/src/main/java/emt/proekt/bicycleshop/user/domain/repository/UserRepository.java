package emt.proekt.bicycleshop.user.domain.repository;

import emt.proekt.bicycleshop.user.domain.model.User;
import emt.proekt.bicycleshop.user.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
}
