package com.personal.InventionMarket.service;

import com.personal.InventionMarket.dto.UserDTO;
import com.personal.InventionMarket.exception.userExceptions.ResourceDetailsInvalidException;
import com.personal.InventionMarket.exception.userExceptions.ResourceNotFoundException;
import com.personal.InventionMarket.model.User;
import com.personal.InventionMarket.repository.UserRepository;
import com.personal.InventionMarket.utility.StringUtil;
import com.personal.InventionMarket.utility.Utils;
import com.personal.InventionMarket.utility.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        validateUserDetails(user, Optional.empty());
        return userRepository.save(user);
    }

    public User getUserDetails(String userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }


    public User updateUserDetails(String userId, UserDTO newUserDetailsDTO) {
        User userDetailsInDB = getUserDetails(userId);
        User givenUserDetails = UserMapper.mapToUser(newUserDetailsDTO);
        User updatedUser = UserMapper.mapSrcUserToTargetUser(givenUserDetails, userDetailsInDB);
        validateUserDetails(updatedUser, Optional.ofNullable(userId));
        return userRepository.save(updatedUser);
    }

    private void validateUserDetails(User user, Optional<String> currentUserId) {
        Optional<User> availableUser;

        if (user.getUsername() == null) {
            throw new ResourceDetailsInvalidException("Username missing");
        } else if (user.getEmail() == null) {
            throw new ResourceDetailsInvalidException("Email missing");
        } else if (!Utils.validateEmail(user.getEmail())) {
            throw new ResourceDetailsInvalidException("Email is Invalid");
        } else if (user.getPhone() == null) {
            throw new ResourceDetailsInvalidException("Phone Number missing");
        } else if (!Utils.validatePhoneNumber(user.getPhone())) {
            throw new ResourceDetailsInvalidException("Phone Number is Invalid");
        } else if (user.getRole() == null) {
            throw new ResourceDetailsInvalidException("Role is missing");
        }
        if ((availableUser = userRepository.findByUsername(user.getUsername())).isPresent()
                && (!currentUserId.isPresent()
                    || StringUtil.isEmpty(availableUser.get().getUserId())
                    || !availableUser.get().getUserId().equalsIgnoreCase(currentUserId.get()))) {

            throw new ResourceDetailsInvalidException("Username already present");

        } else if ((availableUser = userRepository.findByEmail(user.getEmail())).isPresent()
                && (!currentUserId.isPresent()
                    || StringUtil.isEmpty(availableUser.get().getUserId())
                    || !availableUser.get().getUserId().equalsIgnoreCase(currentUserId.get()))) {

            throw new ResourceDetailsInvalidException("Email already present");

        } else if ((availableUser = userRepository.findByPhone(user.getPhone())).isPresent()
                && (!currentUserId.isPresent()
                    || StringUtil.isEmpty(availableUser.get().getUserId())
                    || !availableUser.get().getUserId().equalsIgnoreCase(currentUserId.get()))) {

            throw new ResourceDetailsInvalidException("Phone number already present");
        }
    }

}
