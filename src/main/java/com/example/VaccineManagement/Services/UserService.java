package com.example.VaccineManagement.Services;

import com.example.VaccineManagement.Dtos.UpdateEmailDto;
import com.example.VaccineManagement.Models.Dose;
import com.example.VaccineManagement.Models.User;
import com.example.VaccineManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(User user) {
        userRepository.save(user);
        return "user added Successfully";
    }

    public Date getVaccinationDate(Integer userId) {
        User user = userRepository.findById(userId).get();
        Dose dose = user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDto updateEmailDto) {
        int userId = updateEmailDto.getUserId();
        User user = userRepository.findById(userId).get();

        user.setEmailId(updateEmailDto.getNewEmailId());
        userRepository.save(user);

        return "Email updated successfully with new one ->"+updateEmailDto.getNewEmailId();
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmailId(email);
        return user;
    }
}
