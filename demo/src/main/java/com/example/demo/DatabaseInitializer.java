package com.example.demo;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    public void initializeDatabase() {
        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRole.setUsername(user.getUsername());
        userRole.setPassword(user.getPassword());
        userRoleRepository.save(userRole);
    }
}