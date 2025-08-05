package com.alexandre.user_registration.business;

import com.alexandre.user_registration.infra.entities.User;
import com.alexandre.user_registration.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.saveAndFlush(user);
    }

    public User getUserByEmail(String userEmail){
        return userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new RuntimeException("Email not found.")
        );
    }

    public User getUserById(Integer userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }

    public void deleteUserById(Integer userId){
        userRepository.deleteById(userId);
    }

    public void updateUserById(Integer userId, User user){
        User userEntity = getUserById(userId);
        User updatedUser = User.builder()
                .email(user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .name(user.getName() != null ? user.getName() : userEntity.getName())
                .id(userEntity.getId())
                .build();
        userRepository.saveAndFlush(updatedUser);
    }
}
