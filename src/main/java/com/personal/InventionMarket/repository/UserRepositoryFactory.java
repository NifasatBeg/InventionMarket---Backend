package com.personal.InventionMarket.repository;

import com.personal.InventionMarket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryFactory {

    @Autowired
    private InventorRepository inventorRepository;

    public UserRepository<? extends User> getValidUserRepository(String username){
        if(isInventorUsername(username)){
            return inventorRepository;
        }else if(isBusinessUserName(username)){
            return null;
        }else{
            throw new IllegalArgumentException("Unknown user type for username: " + username);
        }
    }

    private Boolean isInventorUsername(String username){
        return inventorRepository.findByUsername(username).isPresent();
    }

    private Boolean isBusinessUserName(String username){
        return false;
    }
}
