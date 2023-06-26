package com.example.VaccineManagement.Services;

import com.example.VaccineManagement.Dtos.AppointmentReqDto;
import com.example.VaccineManagement.Exceptions.DoctorNotFound;
import com.example.VaccineManagement.Exceptions.UserNotFound;
import com.example.VaccineManagement.Models.Appointment;
import com.example.VaccineManagement.Models.Doctor;
import com.example.VaccineManagement.Models.User;
import com.example.VaccineManagement.Repositories.AppointmentRepository;
import com.example.VaccineManagement.Repositories.DoctorRepository;
import com.example.VaccineManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    private JavaMailSender emailSender;

    public String bookAppointment(AppointmentReqDto appointmentReqDto) throws DoctorNotFound, UserNotFound {
        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentReqDto.getDocId());
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFound("Incorrect Doctor id");
        }
        Optional<User> userOptional = userRepository.findById(appointmentReqDto.getUserId());
        if(userOptional.isEmpty()) throw new UserNotFound("Incorrect User Id");

        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();

        Appointment appointment = new Appointment();

        appointment.setUser(user);
        appointment.setDoctor(doctor);

        appointment.setAppointmentDate(appointmentReqDto.getAppointmentDate());
        appointment.setAppointmentTime((appointmentReqDto.getAppointmentTime()));

       appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);

//        String body = "Hi ! "+user.getName()+"\n"+
//                "You have successfully booked an appointment with "+doctor.getName()+" for covid vaccine."+"\n"+
//                "please reach out to "+doctor.getVaccinationCenter().getAddress()+", by "+appointment.getAppointmentDate()+" "+appointment.getAppointmentTime()+"\n"+
//                "Thank you.";
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("hari.receiving@gmail.com");
//        mailMessage.setSubject("Appointment confirmed");
//        mailMessage.setTo(user.getEmailId());
//        mailMessage.setText(body);
//
//        emailSender.send(mailMessage);

        return  "Appointment Booked Successfully";
    }


}
