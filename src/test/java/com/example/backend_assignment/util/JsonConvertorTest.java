package com.example.backend_assignment.util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonConvertorTest {

    @Autowired
    private JsonConvertor jsonConvertor;

    @Test
    public void testConvertCsvToCompensationRecord(){
        jsonConvertor.convertJsonToCompensationRecord("/Users/liuyenhong/Desktop/Backend_assignment/src/main/resources/compensationData/salary_survey-1.json");
    }
}