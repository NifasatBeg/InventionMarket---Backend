package com.personal.InventionMarket.utility.mapper;

import com.personal.InventionMarket.dto.InventorDTO;
import com.personal.InventionMarket.model.Inventor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InventorMapper{
    public static Inventor mapSrcInventorToTargetInventor(Inventor srcInventor, Inventor targetInventor){
        UserMapper.mapSrcUserToTargetUser(srcInventor, targetInventor);

        //need to set it this way as JPA has reference of allInventions in inventorInDB, changing the reference causes issues as JPA loses reference to old AllInventions.
        //so cannot set it as targetInventor.setAllInventions(srcInventor.getAllInventions)

        if(srcInventor.getAllInvention() != null){
            if(targetInventor.getAllInvention() != null)
                targetInventor.getAllInvention().clear();
            srcInventor.getAllInvention().stream().forEach(invention -> {
                targetInventor.addInvention(invention);
            });
        }
        return targetInventor;
    }

    public static Inventor inventorDtoToInventor(PasswordEncoder passwordEncoder, InventorDTO inventorDTO, ModelMapper modelMapper){
        Inventor inventor = modelMapper.map(UserMapper.userDtoToUser(passwordEncoder, inventorDTO), Inventor.class);
        inventor.setAllInvention(inventorDTO.getAllInvention());

        return inventor;
    }
}
