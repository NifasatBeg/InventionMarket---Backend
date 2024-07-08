package com.personal.InventionMarket.service;

import com.personal.InventionMarket.dto.InventorDTO;
import com.personal.InventionMarket.exception.userExceptions.ResourceNotFoundException;
import com.personal.InventionMarket.model.Invention;
import com.personal.InventionMarket.model.Inventor;
import com.personal.InventionMarket.repository.InventorRepository;
import com.personal.InventionMarket.utility.mapper.InventorMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class InventorService extends UserService{
    @Autowired
    private InventorRepository inventorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public Inventor addInventor(InventorDTO inventorDTO) {
        log.info("Inside add Inventor");
        Inventor inventor = InventorMapper.inventorDtoToInventor(passwordEncoder, inventorDTO, modelMapper);
        setInventorInInventionsSet(inventor, inventor.getAllInvention());
        validateBasicUserDetails(inventor, Optional.empty(), inventorRepository);

        return inventorRepository.save(inventor);
    }

    public Inventor getSavedInventor(String userId){
        return inventorRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<Inventor> getAllInventors(){
        log.info("Inside get All Inventor");
        return inventorRepository.findAll();
    }

    public Inventor updateInventorDetails(String userId, InventorDTO newInventorDetailsDTO) {
        Inventor inventorInDB = getSavedInventor(userId);
        Inventor givenInventorDetails = InventorMapper.inventorDtoToInventor(passwordEncoder, newInventorDetailsDTO, modelMapper);
        Inventor updatedInventor = InventorMapper.mapSrcInventorToTargetInventor(givenInventorDetails, inventorInDB);
        setInventorInInventionsSet(updatedInventor, updatedInventor.getAllInvention());
        validateBasicUserDetails(updatedInventor, Optional.ofNullable(userId),inventorRepository);
        return inventorRepository.save(updatedInventor);
    }

    public void deleteInventor(String userId){
        inventorRepository.deleteById(userId);
    }


    // need to add inventor for inventions as JPA does not manage that as it could lead unnecessary join queries.
    private void setInventorInInventionsSet(Inventor inventor, Set<Invention> inventions){
        if(inventions == null)return;
        Iterator<Invention> iterator = inventions.iterator();
        while (iterator.hasNext()) {
            iterator.next().setInventor(inventor);
        }
    }

}
