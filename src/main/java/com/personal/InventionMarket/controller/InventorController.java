package com.personal.InventionMarket.controller;

import com.personal.InventionMarket.dto.InventorDTO;
import com.personal.InventionMarket.model.Inventor;
import com.personal.InventionMarket.service.InventorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class InventorController {

    @Autowired
    private InventorService inventorService;

    @PostMapping("/inventor/register")
    public ResponseEntity<Inventor> addInventor(@RequestBody InventorDTO inventorDTO){
        log.debug("Inside controller adding inventor");
        Inventor inventor = inventorService.addInventor(inventorDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventor);
    }

    @GetMapping("/inventor/{userId}")
    public ResponseEntity<Inventor> getSavedInventor(@PathVariable String userId){
        log.debug("Inside controller getting inventor");
        Inventor inventor = inventorService.getSavedInventor(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventor);
    }

    @GetMapping("/inventors")
    public ResponseEntity<List<Inventor>> getAllInventors(){
        log.debug("Inside controller getting all Inventors");
        List<Inventor> allInventors = inventorService.getAllInventors();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allInventors);
    }

    @PutMapping("/updateInventor/{userId}")
    public ResponseEntity<Inventor> updateInventorDetails(@PathVariable String userId, @RequestBody InventorDTO newInventorDetailsDTO){
        log.debug("Inside controller updating inventor");
        Inventor inventor = inventorService.updateInventorDetails(userId, newInventorDetailsDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventor);
    }

    @PutMapping("/deleteInventor/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        log.debug("Inside controller deleting inventor");
        inventorService.deleteInventor(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Inventor deleted");
    }

}
