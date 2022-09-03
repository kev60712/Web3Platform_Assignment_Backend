package com.example.backend_assignment.Model.repo;

import com.example.backend_assignment.Model.dao.CompensationRecordMapper;
import com.example.backend_assignment.Model.dao.CompensationRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CompensationRepo {

    private static Logger logger = LoggerFactory.getLogger(CompensationRepo.class);

    @Value("#{${db.compensation.query.column.variable.map}}")
    private Map<String, String> colVarMap;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String composeSql(List<String> values, String sort){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM CompensationRecord WHERE 1 = 1 ");
        List<String> colNames = new ArrayList<>(colVarMap.keySet());
        List<String> colVars = new ArrayList<>(colVarMap.values());
        for (int i = 0; i < values.size(); i++){
            if (values.get(i) != null){
                sb.append(String.format("AND %s = %s ", colNames.get(i), colVars.get(i)));
            }
        }
        if (sort != null && colVarMap.containsKey(sort.toLowerCase())){
            sb.append(String.format("ORDER BY %s", sort.toLowerCase()));
        }
        return sb.toString();
    }

    public List<CompensationRecord> findRecordsByValues(List<String> values, String sort){
        List<CompensationRecord> records = new ArrayList<>();
        try{
            String sql = composeSql(values, sort);
            Map<String, Object> parm = new HashMap<>();
            parm.put("age", values.get(0));
            parm.put("industry", values.get(1));
            parm.put("currency", values.get(2));
            parm.put("location", values.get(3));
            records = namedParameterJdbcTemplate.query(sql, parm, new CompensationRecordMapper());
        }catch (Exception e){
            logger.error(String.format("[findRecordsByValues] error: %s", e.getMessage()));
        }
        return records;
    }

    public Optional<CompensationRecord> findRecordByFirstNameAndLastNameAndSalary(String firstName, String lastName, String salary){
        List<CompensationRecord> queryResult = new ArrayList<>();
        try {
            String sql = "SELECT t.* FROM CompensationRecord WHERE t.FIRST_NAME = :firstName AND t.LAST_NAME = :lastName AND t.SALARY = :salary";
            Map<String, Object> parm = new HashMap<>();
            parm.put("firstName", firstName);
            parm.put("lastName", lastName);
            parm.put("salary", salary);
            queryResult = namedParameterJdbcTemplate.query(sql, parm, new CompensationRecordMapper());
        }catch (Exception e){
            logger.error(String.format("[findRecordByFirstNameAndLastNameAndSalary] error: %s", e.getMessage()));
        }
        return queryResult.isEmpty() ? Optional.empty() : Optional.of(queryResult.get(0));
    }

    public void insertRecords(List<CompensationRecord> records){
        try{
            String sql = composeInsertSql();
            MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[records.size()];

            for (int i = 0; i < records.size(); i++){
                CompensationRecord record = records.get(i);
                parameterSources[i] = new MapSqlParameterSource();
                parameterSources[i].addValue("recordTime", record.getRecordTime());
                parameterSources[i].addValue("age", record.getAge());
                parameterSources[i].addValue("industry", record.getIndustry());
                parameterSources[i].addValue("jobTitle", record.getJobTitle());
                parameterSources[i].addValue("annualSalary", record.getAnnualSalary());
                parameterSources[i].addValue("currency", record.getCurrency());
                parameterSources[i].addValue("location", record.getLocation());
                parameterSources[i].addValue("postGradWrkExperience", record.getPostGradWrkExperience());
                parameterSources[i].addValue("additionalWrkInfo", record.getAdditionalWrkInfo());
                parameterSources[i].addValue("other", record.getOther());
            }

             namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
        }catch (Exception e) {
            logger.error(String.format("[insertRecords] error: %s", e.getMessage()));
        }
    }

    public String composeInsertSql(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO CompensationRecord (recordTime, age, industry, jobTitle, annualSalary, currency, location, postGradWrkExperience, additionalWrkInfo, other) ");
        sb.append("VALUES (:recordTime, :age, :industry, :jobTitle, :annualSalary, :currency, :location, :postGradWrkExperience, :additionalWrkInfo, :other) ");
        return sb.toString();
    }
}
