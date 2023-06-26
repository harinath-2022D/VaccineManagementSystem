package com.example.VaccineManagement.Repositories;

import com.example.VaccineManagement.Models.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationCenter,Integer> {
}
