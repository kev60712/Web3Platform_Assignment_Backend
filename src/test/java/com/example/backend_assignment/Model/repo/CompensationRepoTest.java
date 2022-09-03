package com.example.backend_assignment.Model.repo;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import com.example.backend_assignment.util.JsonConvertor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompensationRepoTest {

    @Autowired
    private CompensationRepo compensationRepo;

    @Autowired
    private JsonConvertor jsonConvertor;

    @Test
    public void testComposeSql(){
        List<String> values = Arrays.asList("18", "IT", "US", "USA");
        String sort = "age";
        String expSql = "SELECT * FROM CompensationRecord WHERE 1 = 1 AND age = :age AND industry = :industry AND currency = :currency AND location = :location ORDER BY age";
        String sql = compensationRepo.composeSql(values, sort);
        assertEquals(expSql, sql);
    }

//    @Test // Just for insert json data to DB
//    public void testInsertRecords(){
//        List<CompensationRecord> compensationRecords = jsonConvertor.convertJsonToCompensationRecord("/Users/liuyenhong/Desktop/Backend_assignment/src/main/resources/compensationData/salary_survey-1.json");
//        compensationRepo.insertRecords(compensationRecords);
//    }
}