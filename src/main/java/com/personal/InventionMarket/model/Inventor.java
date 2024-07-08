package com.personal.InventionMarket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Inventor extends User{
    @OneToMany(mappedBy = "inventor",cascade =CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Invention> allInvention = new HashSet<>();

    public void setAllInvention(Set<Invention> allInventions){
        this.allInvention = allInventions;
    }

    public Set<Invention> getAllInvention(){
        return this.allInvention;
    }

    public void addInvention(Invention invention){
        allInvention.add(invention);
    }

}
