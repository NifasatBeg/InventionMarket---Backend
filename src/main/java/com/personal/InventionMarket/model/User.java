package com.personal.InventionMarket.model;

import com.personal.InventionMarket.model.enums.InventionCategoryEnum;
import com.personal.InventionMarket.model.enums.RoleEnum;
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
@Inheritance(strategy = InheritanceType.JOINED)
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

    @ElementCollection(targetClass = RoleEnum.class)
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name="id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Set<RoleEnum> roles;

    @Embedded
    private Address address;

    private Integer ratings;

    @ElementCollection(targetClass = InventionCategoryEnum.class)
    @CollectionTable(name = "user_interested_categories", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "invention_category")
    private Set<InventionCategoryEnum> interestedCategories;

}
