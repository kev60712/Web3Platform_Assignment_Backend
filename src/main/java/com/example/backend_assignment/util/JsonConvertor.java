package com.example.backend_assignment.util;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Component
public class JsonConvertor {

    private static Logger logger = Logger.getLogger(JsonConvertor.class.getName());

    private ObjectMapper obm = new ObjectMapper();

    // This function is to convert Json file to java object
    public List<CompensationRecord> convertJsonToCompensationRecord(String filePath) {
        List<CompensationRecord> records = new ArrayList<>();
        try{
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            String jsonStr = jsonArray.toJSONString();
            records = obm.readValue(jsonStr, new TypeReference<List<CompensationRecord>>() {});
        }catch (Exception e){
            logger.info(String.format("[convertJsonToCompensationRecord] error", e.getMessage()));
        }
        return records;
    }
}
