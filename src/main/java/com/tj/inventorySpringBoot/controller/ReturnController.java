package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ReturnDTO;
import com.tj.inventorySpringBoot.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    // Create a new return
    @PostMapping("/create")
    public ResponseEntity<ReturnDTO> createReturn(@RequestBody ReturnDTO returnDTO) {
        ReturnDTO createdReturn = returnService.createReturn(returnDTO);
        return ResponseEntity.ok(createdReturn);
    }

    // Update an existing return by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnDTO> updateReturn(@PathVariable Long id, @RequestBody ReturnDTO returnDTO) {
        ReturnDTO updatedReturn = returnService.updateReturn(id, returnDTO);
        if (updatedReturn != null) {
            return ResponseEntity.ok(updatedReturn);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all returns
    @GetMapping
    public ResponseEntity<List<ReturnDTO>> getAllReturns() {
        List<ReturnDTO> returnList = returnService.getAllReturns();
        return ResponseEntity.ok(returnList);
    }

    // Get a specific return by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReturnDTO> getReturnById(@PathVariable Long id) {
        ReturnDTO returnDTO = returnService.getReturnById(id);
        if (returnDTO != null) {
            return ResponseEntity.ok(returnDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a return by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long id) {
        returnService.deleteReturn(id);
        return ResponseEntity.noContent().build();
    }
}
