package com.personal.InventionMarket.utility.mapper;

import com.personal.InventionMarket.dto.UserDTO;
import com.personal.InventionMarket.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User userDtoToUser(PasswordEncoder passwordEncoder, UserDTO userDTO){
        return User.builder().userId(userDTO.getUserId())
                .username(userDTO.getUsername())
                .hashedPassword(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .roles(userDTO.getRoles())
                .interestedCategories(userDTO.getInterestedCategories())
                .address(userDTO.getAddress())
                .build();
    }

    public static User mapSrcUserToTargetUser(User srcUser, User targetUser) {
        if (srcUser.getUsername() != null) {
            targetUser.setUsername(srcUser.getUsername());
        }
        if (srcUser.getHashedPassword() != null) {
            targetUser.setHashedPassword(srcUser.getHashedPassword());
        }
        if (srcUser.getEmail() != null) {
            targetUser.setEmail(srcUser.getEmail());
        }
        if (srcUser.getPhone() != null) {
            targetUser.setPhone(srcUser.getPhone());
        }
        if (srcUser.getAddress() != null) {
            targetUser.setAddress(srcUser.getAddress());
        }
        if (srcUser.getRatings() != null) {
            targetUser.setRatings(srcUser.getRatings());
        }
        if(srcUser.getInterestedCategories() != null){
            targetUser.setInterestedCategories(srcUser.getInterestedCategories());
        }
        if(srcUser.getRoles() != null){
            targetUser.setRoles(srcUser.getRoles());
        }

        return targetUser;
    }

}
