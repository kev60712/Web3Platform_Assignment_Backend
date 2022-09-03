package com.example.backend_assignment.Controller;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import com.example.backend_assignment.Service.CompensationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompensationControllerTest {

    @Autowired
    private CompensationController compensationController;

    @MockBean
    private CompensationService compensationService;

    // To Test response is in JSON format
    @Test
    public void testGetCompensationRecords()  {
        CompensationRecord record1 = new CompensationRecord("24", "US","IT");
        CompensationRecord record2 = new CompensationRecord("28", "US","Accounting");

        Mockito.when(compensationService.findRecordsByValues(Mockito.any(), Mockito.any()))
                .thenReturn(Arrays.asList(record1, record2));

        ResponseEntity response = compensationController.getCompensationRecords(null, "US", null, null, null, null);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("[{\"How old are you?\":\"24\",\"What industry do you work in?\":\"US\",\"Please indicate the currency\":\"IT\"},{\"How old are you?\":\"28\",\"What industry do you work in?\":\"US\",\"Please indicate the currency\":\"Accounting\"}]",
                response.getBody());
    }

    @Test
    public void testGetCompensationRecord()  {
        CompensationRecord record = new CompensationRecord("24", "IT","US");
        Mockito.when(compensationService.findRecordByFirstNameAndLastNameAndSalary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(record));

        ResponseEntity response = compensationController.getCompensationRecord("Kevin,Liu,80000");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("{\"How old are you?\":\"24\",\"What industry do you work in?\":\"IT\",\"Please indicate the currency\":\"US\"}",
                response.getBody());
    }

}