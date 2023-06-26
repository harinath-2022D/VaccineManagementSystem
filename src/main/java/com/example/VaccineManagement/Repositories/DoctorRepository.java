package com.example.VaccineManagement.Repositories;

import com.example.VaccineManagement.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {


    Doctor findByEmailId(String emailId);

    @Query(value = "select doctor_doc_id from appointments group by doctor_doc_id having count(doctor_doc_id) >= :appoints ;",nativeQuery = true)
    List<Integer> getAllDoctorsAppointmentsGreaterThan(int appoints);

}
