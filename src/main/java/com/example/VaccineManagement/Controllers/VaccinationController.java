package com.example.VaccineManagement.Controllers;

import com.example.VaccineManagement.Exceptions.VaccinationAddressNotFoundException;
import com.example.VaccineManagement.Models.Doctor;
import com.example.VaccineManagement.Models.VaccinationCenter;
import com.example.VaccineManagement.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {
    @Autowired
    VaccinationService vaccinationService;
    @PostMapping("/add-center")
    public ResponseEntity<String> addCenter(@RequestBody VaccinationCenter vaccinationCenter){
        try {
            String response =vaccinationService.addCenter(vaccinationCenter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (VaccinationAddressNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
