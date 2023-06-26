package com.example.VaccineManagement.Controllers;

import com.example.VaccineManagement.Dtos.AssociateDocDto;
import com.example.VaccineManagement.Dtos.TransferDoctorDto;
import com.example.VaccineManagement.Models.Doctor;
import com.example.VaccineManagement.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add-doctor")
    public String addDoctor(@RequestBody Doctor doctor){
        try{
            String response = doctorService.addDoctor(doctor);
            return response;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/associateWithCenter")
    public ResponseEntity<String> associateWithCenter(@RequestBody AssociateDocDto associateDocDto){
        try{
            String response = doctorService.associateWithCenter(associateDocDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getListOfDoctors")
    public List<Doctor> getListOfDoctors(@RequestParam("centerId") int id){
        List<Doctor> doctorList = doctorService.getListOfDoctors(id);
        return doctorList;
    }

    @GetMapping("/getListOfMaleDoctors")
    public List<Doctor> getListOfMaleDoctors(@RequestParam("centerId") int id){
        List<Doctor> maleDoctorList = doctorService.getListOfMaleDoctors(id);
        return maleDoctorList;
    }

    @GetMapping("/getListOfMaleDoctorsAgeAbove40")
    public List<Doctor> getListOfMaleDoctorsAgeAbove(@RequestParam("centerId") int id,@RequestParam("age") int age){
        List<Doctor> maleDoctorList = doctorService.getListOfMaleDoctorsAgeAbove(id,age);
        return maleDoctorList;
    }

    @GetMapping("/getListOfFemaleDoctors")
    public List<Doctor> getListOfFemaleDoctors(@RequestParam("centerId") int id){
        List<Doctor> femaleDoctorList = doctorService.getListOfFemaleDoctors(id);
        return femaleDoctorList;
    }

    @GetMapping("/ratioOfMaleAndFemale")
    public String getRatioOfMaleAndFemaleDoctors(){
        String str = doctorService.getRatioOfMaleAndFemaleDoctors();
        return str;
    }

    @PutMapping("/transferDoctor")
    public String transferDoctor(@RequestBody TransferDoctorDto transferDoctorDto){
        return doctorService.transferDoctor(transferDoctorDto);
    }

    @GetMapping("/doctorsAppointmentsGreaterThan")
    public List<String> getDoctorsWhosAppointmentsGreaterThan(@RequestParam("appointments") int appoints){
        List<String> doctors = doctorService.getDoctorsWhosAppointmentsGreaterThan(appoints);
        return doctors;
    }
}
