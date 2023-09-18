package com.springquest02.demo.repositories;

import com.springquest02.demo.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findById(int id);
}
