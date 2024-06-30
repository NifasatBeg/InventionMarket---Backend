package com.personal.InventionMarket.model;

import com.personal.InventionMarket.model.enums.InventionCategoryEnum;
import com.personal.InventionMarket.model.enums.InventorRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @UuidGenerator
    private String userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InventorRoleEnum role;

    @Embedded
    private Address address;

    private Integer ratings;

    @ElementCollection(targetClass = InventionCategoryEnum.class)
    @CollectionTable(name = "user_interested_categories", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "invention_category")
    private Set<InventionCategoryEnum> interestedCategories;

}
