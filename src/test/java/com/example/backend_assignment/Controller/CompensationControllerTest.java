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

    @Test
    public void testGetCompensationRecords() throws JsonProcessingException {
        CompensationRecord record1 = new CompensationRecord("24", "US","IT");
        CompensationRecord record2 = new CompensationRecord("28", "US","Accounting");

        Mockito.when(compensationService.findRecordsByValues(Mockito.any(), Mockito.any()))
                .thenReturn(Arrays.asList(record1, record2));

        ResponseEntity response = compensationController.getCompensationRecords(null, "US", null, null, null, null);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("[{\"age\":\"24\",\"industry\":\"US\",\"currency\":\"IT\"},{\"age\":\"28\",\"industry\":\"US\",\"currency\":\"Accounting\"}]",
                response.getBody());
    }

    @Test
    public void testGetCompensationRecord() throws JsonProcessingException {
        CompensationRecord record = new CompensationRecord("24", "US","IT");
        Mockito.when(compensationService.findRecordByFirstNameAndLastNameAndSalary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(record));

        ResponseEntity response = compensationController.getCompensationRecord("Kevin,Liu,80000");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("{\"age\":\"24\",\"industry\":\"IT\",\"currency\":\"US\"}",
                response.getBody());
    }

}