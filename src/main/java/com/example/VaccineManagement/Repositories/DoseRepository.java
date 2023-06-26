package com.example.VaccineManagement.Repositories;

import com.example.VaccineManagement.Models.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepository extends JpaRepository<Dose,String> {
}
