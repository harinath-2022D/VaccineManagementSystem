package com.example.VaccineManagement.Repositories;

import com.example.VaccineManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailId(String email); // custom searching you have to define in repository
}
