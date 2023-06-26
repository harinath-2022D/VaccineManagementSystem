package com.example.VaccineManagement.Services;

import com.example.VaccineManagement.Exceptions.VaccinationAddressNotFoundException;
import com.example.VaccineManagement.Models.Doctor;
import com.example.VaccineManagement.Models.VaccinationCenter;
import com.example.VaccineManagement.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationService {
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addCenter(VaccinationCenter vaccinationCenter)throws VaccinationAddressNotFoundException {
        if(vaccinationCenter.getAddress() == null){
            throw new VaccinationAddressNotFoundException("Vaccination Center Address is EMPTY");
        }

        vaccinationRepository.save(vaccinationCenter);
        return "Vaccination Center haas been added Successfully at given location"+vaccinationCenter.getAddress();
    }

}
