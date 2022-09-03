package com.example.backend_assignment.Service;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import com.example.backend_assignment.Model.repo.CompensationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompensationService {

    @Autowired
    private CompensationRepo compensationRepo;

    public Optional<CompensationRecord> findRecordByFirstNameAndLastNameAndSalary(String firstName, String lastName, String salary){
        return compensationRepo.findRecordByFirstNameAndLastNameAndSalary(firstName, lastName, salary);
    }

    public List<CompensationRecord> findRecordsByValues(List<String> values, String sort) {
        return compensationRepo.findRecordsByValues(values, sort);
    }
}
