package com.personal.InventionMarket.service;

import com.personal.InventionMarket.exception.userExceptions.ResourceDetailsInvalidException;
import com.personal.InventionMarket.model.User;
import com.personal.InventionMarket.model.enums.RoleEnum;
import com.personal.InventionMarket.repository.UserRepository;
import com.personal.InventionMarket.repository.UserRepositoryFactory;
import com.personal.InventionMarket.utility.StringUtil;
import com.personal.InventionMarket.utility.Utils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepositoryFactory userRepositoryFactory;

    public void validateBasicUserDetails(User user, Optional<String> currentUserId, UserRepository userRepository) {
        Optional<User> availableUser;

        if (user.getUsername() == null) {
            throw new ResourceDetailsInvalidException("Username missing");
        } else if (user.getHashedPassword() == null){
            throw new ResourceDetailsInvalidException("Password missing");
        }else if (user.getEmail() == null) {
            throw new ResourceDetailsInvalidException("Email missing");
        } else if (!Utils.validateEmail(user.getEmail())) {
            throw new ResourceDetailsInvalidException("Email is Invalid");
        } else if (user.getPhone() == null) {
            throw new ResourceDetailsInvalidException("Phone Number missing");
        } else if (!Utils.validatePhoneNumber(user.getPhone())) {
            throw new ResourceDetailsInvalidException("Phone Number is Invalid");
        } else if(user.getRoles() == null){
            throw new ResourceDetailsInvalidException("Role is missing");
        } else if(user.getRoles() != null){
            if(user.getRoles().contains(RoleEnum.INVENTOR) && user.getRoles().contains(RoleEnum.BUSINESS)){
                throw new ResourceDetailsInvalidException("Role cannot be both inventor and business");
            }
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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository userRepository = userRepositoryFactory.getValidUserRepository(username);
        User user = null;
        try {
            user = (User) userRepository.findByUsername(username)
                    .orElseThrow(()->new RuntimeException("Username not found"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
                .password(user.getHashedPassword())
                .roles(user.getRoles().stream().map(RoleEnum::name).toArray(String[]::new))
                .build();
    }
}
