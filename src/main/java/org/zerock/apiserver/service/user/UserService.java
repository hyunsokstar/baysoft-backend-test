package org.zerock.apiserver.service.user;

import org.zerock.apiserver.domain.auth.User;

public interface UserService {
    User findByUsername(String username);
}