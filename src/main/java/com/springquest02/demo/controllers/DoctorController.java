package com.springquest02.demo.controllers;

import com.springquest02.demo.dto.DoctorDetails;
import com.springquest02.demo.entities.Doctor;
import com.springquest02.demo.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/doctor/{incarnationNumber}")
    public ResponseEntity<Object> getDoctorDetails(@PathVariable int incarnationNumber) {
        if (incarnationNumber >= 9 && incarnationNumber <= 13) {
            Doctor doctor = doctorRepository.findById(incarnationNumber);

                // Doctors 9 to 13: Return details in JSON format
                DoctorDetails doctorDetails = new DoctorDetails(doctor.getId(), doctor.getName());
                return ResponseEntity.ok(doctorDetails);

        } else if (incarnationNumber >= 1 && incarnationNumber <= 8) {
            // Doctors 1 to 8: Return a 303 status
            return ResponseEntity.status(HttpStatus.SEE_OTHER).build();
        }
        // Invalid incarnation number: Return a 404 status
        String errorMessage = "Impossible to retrieve the incarnation " + incarnationNumber;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
