package org.zerock.apiserver.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.apiserver.domain.auth.User;
import org.zerock.apiserver.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    public CustomUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .accountExpired(!user.isEnabled())
                .accountLocked(!user.isEnabled())
                .credentialsExpired(!user.isEnabled())
                .disabled(!user.isEnabled())
                .build();
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(userDetails.isEnabled());
        // Roles should be set here based on the authorities
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        userRepository.findByUsername(userDetails.getUsername()).ifPresent(user -> {
            user.setPassword(userDetails.getPassword());
            user.setEnabled(userDetails.isEnabled());
            // Update roles if necessary
            userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.findByUsername(username).ifPresent(user -> userRepository.delete(user));
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // This method is typically called for the currently authenticated user
        // You might want to add authentication checks here
        throw new UnsupportedOperationException("Change password operation not supported");
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}