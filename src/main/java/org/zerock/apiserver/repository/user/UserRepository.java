package org.zerock.apiserver.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.auth.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}