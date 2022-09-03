package com.example.backend_assignment.util;
import com.example.backend_assignment.Model.dao.CompensationRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JsonConvertorTest {

    @Autowired
    private JsonConvertor jsonConvertor;

    @Test
    public void testConvertCsvToCompensationRecord(){
        List<CompensationRecord> recordList = jsonConvertor.convertJsonToCompensationRecord("/Users/liuyenhong/Desktop/Backend_assignment/src/main/resources/compensationData/salary_survey-1.json");
        assertEquals(34374, recordList.size());
    }
}