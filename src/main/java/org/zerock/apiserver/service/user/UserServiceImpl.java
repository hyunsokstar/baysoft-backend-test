package org.zerock.apiserver.service.user;

import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.auth.User;
import org.zerock.apiserver.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}