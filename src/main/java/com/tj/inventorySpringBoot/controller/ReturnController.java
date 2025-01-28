package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ReturnDTO;
import com.tj.inventorySpringBoot.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    // Endpoint to create or update a return
    @PostMapping
    public ResponseEntity<ReturnDTO> createOrUpdateReturn(@RequestBody ReturnDTO returnDTO) {
        ReturnDTO savedReturn = returnService.saveReturn(returnDTO);
        return new ResponseEntity<>(savedReturn, HttpStatus.CREATED);
    }

    // Endpoint to get all returns
    @GetMapping
    public ResponseEntity<List<ReturnDTO>> getAllReturns() {
        List<ReturnDTO> returns = returnService.getAllReturns();
        return new ResponseEntity<>(returns, HttpStatus.OK);
    }

    // Endpoint to get a return by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ReturnDTO> getReturnById(@PathVariable Long id) {
        ReturnDTO returnDTO = returnService.getReturnById(id);
        if (returnDTO != null) {
            return new ResponseEntity<>(returnDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
    }

    // Endpoint to delete a return by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long id) {
        returnService.deleteReturn(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
    }
}

