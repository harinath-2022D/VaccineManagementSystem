package com.example.VaccineManagement.Repositories;

import com.example.VaccineManagement.Models.Appointment;
import com.example.VaccineManagement.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}
