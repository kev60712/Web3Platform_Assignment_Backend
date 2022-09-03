package com.example.backend_assignment.Controller;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import com.example.backend_assignment.Model.repo.CompensationRepo;
import com.example.backend_assignment.Service.CompensationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.json.JsonSanitizer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CompensationController {

    @Value("#{${db.compensation.query.column.variable.map}}")
    private Map<String, String> colVarMap;

    @Autowired
    private CompensationService compensationService;

    private ObjectMapper obm = new ObjectMapper();

    @GetMapping("/compensationRecords")
    public ResponseEntity<Object> getCompensationRecords(@RequestParam(required = false) String age,
                                               @RequestParam(required = false) String industry,
                                               @RequestParam(required = false) String currency,
                                               @RequestParam(required = false) String location,
                                               @RequestParam(required = false) String workExperience,
                                               @RequestParam(required = false) String sort) {
        try{
            List<String> colValues = Arrays.asList(age, industry, currency, location, workExperience);
            List<CompensationRecord> compensationRecords = compensationService.findRecordsByValues(colValues, sort);
            String jsonStr = obm.writeValueAsString(compensationRecords);
            return new ResponseEntity<>(JsonSanitizer.sanitize(jsonStr), HttpStatus.ACCEPTED);
        }catch (Exception e){
            // TODO: Set Return Error Msg Format
            return new ResponseEntity<>(JsonSanitizer.sanitize("{}"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/compensationRecord")
    public ResponseEntity<Object> getCompensationRecord(@RequestParam String fields) {
        try{
            String[] fieldList = fields.split(",");
            String firstName = fieldList[0];
            String lastName = fieldList[1];
            String salary = fieldList[2];
            Optional<CompensationRecord> result = compensationService.findRecordByFirstNameAndLastNameAndSalary(firstName, lastName, salary);
            if (result.isPresent()){
                CompensationRecord record = result.get();
                String jsonStr = obm.writeValueAsString(record);
                return new ResponseEntity<>(JsonSanitizer.sanitize(jsonStr), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(JsonSanitizer.sanitize("{}"), HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            // TODO: Set Return Error Msg Format
            return new ResponseEntity<>(JsonSanitizer.sanitize("{}"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}