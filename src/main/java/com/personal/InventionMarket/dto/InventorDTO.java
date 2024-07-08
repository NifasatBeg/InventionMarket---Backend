package com.personal.InventionMarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.personal.InventionMarket.model.Address;
import com.personal.InventionMarket.model.Invention;
import com.personal.InventionMarket.model.enums.InventionCategoryEnum;
import com.personal.InventionMarket.model.enums.RoleEnum;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@JsonIgnoreProperties
public class InventorDTO extends UserDTO{

    public InventorDTO(String userId, String username, String password, String email, String phone, Set<RoleEnum>roles, Address address, Set<InventionCategoryEnum> interestedCategories, Set<Invention> allInventions) {
        super(userId, username, password, email, phone, address, roles, interestedCategories);
        this.allInvention = allInventions;
    }

    private Set<Invention> allInvention;

    public Set<Invention> getAllInvention() {
        return allInvention;
    }

    public void setAllInvention(Set<Invention> allInvention) {
        this.allInvention = allInvention;
    }
}
