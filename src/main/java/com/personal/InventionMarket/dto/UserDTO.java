package com.personal.InventionMarket.dto;

import com.personal.InventionMarket.model.Address;
import com.personal.InventionMarket.model.enums.InventionCategoryEnum;
import com.personal.InventionMarket.model.enums.InventorRoleEnum;
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

    private InventorRoleEnum role;

    private Address address;

    private Set<InventionCategoryEnum> interestedCategories;
}
