package com.example.VaccineManagement.Services;

import ch.qos.logback.core.model.INamedModel;
import com.example.VaccineManagement.Dtos.AssociateDocDto;
import com.example.VaccineManagement.Dtos.TransferDoctorDto;
import com.example.VaccineManagement.Exceptions.*;
import com.example.VaccineManagement.Gender;
import com.example.VaccineManagement.Models.Appointment;
import com.example.VaccineManagement.Models.Doctor;
import com.example.VaccineManagement.Models.VaccinationCenter;
import com.example.VaccineManagement.Repositories.DoctorRepository;
import com.example.VaccineManagement.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationRepository vaccinationRepository;
    public String addDoctor(Doctor doctor) throws EmptyEmailIdException, DoctorAlreadyExistException{
        // validation 1
        if(doctor.getEmailId()==null){
            throw new EmptyEmailIdException("EmailId is empty");
        }
        // validation 2
        if(doctorRepository.findByEmailId(doctor.getEmailId())!=null){
            throw new DoctorAlreadyExistException("Doctor with this EmailId already Present");
        }

        doctorRepository.save(doctor);

        return "Doctor has been added Successfully";
    }

    public String associateWithCenter(AssociateDocDto associateDocDto) throws DoctorNotFound, CenterNotFound, DoctorAllreadyAssigned{
        Integer docId = associateDocDto.getDocId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFound("incorrect Doc Id");
        }

        Integer centerId = associateDocDto.getCenterId();
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center is Not yet Registered");
        }

        Doctor doctor = doctorOptional.get();
        VaccinationCenter status = doctor.getVaccinationCenter();
        if(status!=null){
            String body = "Dr."+doctor.getName()+" already associated with center "+status.getCentreName();
            throw new DoctorAllreadyAssigned(body);
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();

        doctor.setVaccinationCenter(vaccinationCenter);// foreign key
        vaccinationCenter.getDoctorList().add(doctor);// bi directional mapping

        vaccinationRepository.save(vaccinationCenter);

        return "Doctor has been Associated with Center";

    }

    public List<Doctor> getListOfDoctors(int id) {
        VaccinationCenter vaccinationCenter = vaccinationRepository.findById(id).get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();
        return doctorList;
    }

    public List<Doctor> getListOfMaleDoctors(int id) {
        VaccinationCenter vaccinationCenter = vaccinationRepository.findById(id).get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();
        List<Doctor> maleDoctors = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.MALE)){
                maleDoctors.add(doctor);
            }
        }
        return maleDoctors;
    }

    public List<Doctor> getListOfMaleDoctorsAgeAbove(int id, int age) {
        VaccinationCenter vaccinationCenter = vaccinationRepository.findById(id).get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();
        List<Doctor> maleDoctors = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.MALE) && doctor.getAge()>=age){
                maleDoctors.add(doctor);
            }
        }
        return maleDoctors;
    }

    public List<Doctor> getListOfFemaleDoctors(int id) {
        VaccinationCenter vaccinationCenter = vaccinationRepository.findById(id).get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();
        List<Doctor> femaleDoctors = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(Gender.FEMALE)){
                femaleDoctors.add(doctor);
            }
        }
        return femaleDoctors;
    }

    private int findGcd(int a, int b){
        if(b == 0) return a;
        else return findGcd(b, a%b);
    }
    public String getRatioOfMaleAndFemaleDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        int maleCnt = 0;
        int femaleCnt = 0;
        for(Doctor doctor : doctorList){

            if(doctor.getGender().equals(Gender.MALE)){
                maleCnt++;
            }else{
                femaleCnt++;
            }

        }

        String ans = "";
        int gcd = findGcd(maleCnt,femaleCnt);
        ans = maleCnt/gcd+":"+femaleCnt/gcd;
        return ans;
    }

    public String transferDoctor(TransferDoctorDto transferDoctorDto) {

        int doctorId = transferDoctorDto.getDoc_id();
        int vaccination_center = transferDoctorDto.getCenter_id();

        Doctor doctor = doctorRepository.findById(doctorId).get();
        VaccinationCenter vaccinationCenter = vaccinationRepository.findById(vaccination_center).get();

        doctor.setVaccinationCenter(vaccinationCenter);
        doctorRepository.save(doctor);

        return  "Dr."+doctor.getName()+" has been transferred to the center "+vaccinationCenter.getCentreName();
    }


    public List<String> getDoctorsWhosAppointmentsGreaterThan(int appoints) {
        List<String> doctors = new ArrayList<>();
        List<Doctor> doctorList = doctorRepository.findAll();
        for(Doctor doctor : doctorList){
            List<Appointment> appointmentList = doctor.getAppointmentList();
            if(appointmentList.size() >= appoints){
            String name = doctor.getName();
            doctors.add(name);}
        }
        return doctors;
    }
}
