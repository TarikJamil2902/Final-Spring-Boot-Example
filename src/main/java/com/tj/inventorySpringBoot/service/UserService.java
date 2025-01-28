package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.UserDTO;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.enums.Role;
import com.tj.inventorySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    // Update an existing user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update fields
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setRole(Role.valueOf(userDTO.getRole())); // Convert role from string to enum
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPhone(userDTO.getPhone());

            User updatedUser = userRepository.save(existingUser);
            return convertToDTO(updatedUser);
        }
        return null; // Handle not found case as needed
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a user by ID
    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Convert UserDTO to User entity
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setRole(Role.valueOf(userDTO.getRole())); // Convert role from string to enum
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return user;
    }

    // Convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().toString()); // Convert enum to string
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }
}
