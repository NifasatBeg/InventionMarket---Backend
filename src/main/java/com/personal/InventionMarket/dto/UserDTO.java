package com.personal.InventionMarket.dto;

import com.personal.InventionMarket.model.Address;
import com.personal.InventionMarket.model.enums.InventionCategoryEnum;
import com.personal.InventionMarket.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Address address;

    private Set<RoleEnum> roles;

    private Set<InventionCategoryEnum> interestedCategories;
}
