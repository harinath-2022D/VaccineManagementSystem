package com.example.VaccineManagement.Services;

import com.example.VaccineManagement.Models.Dose;
import com.example.VaccineManagement.Models.User;
import com.example.VaccineManagement.Repositories.DoseRepository;
import com.example.VaccineManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;
    @Autowired
    UserRepository userRepository;

    public String giveDose( String doseId, Integer userId) {
        User user = userRepository.findById(userId).get();
        Dose dose = new Dose();
        dose.setDoseId(doseId);
        dose.setUser(user);   // foreign key

        user.setDose(dose);
        userRepository.save(user);
        return "dose given successfully to the user id "+userId;
    }
}
