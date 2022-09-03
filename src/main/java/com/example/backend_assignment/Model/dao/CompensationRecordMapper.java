package com.example.backend_assignment.Model.dao;

import com.example.backend_assignment.Model.dao.CompensationRecord;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CompensationRecordMapper implements RowMapper<CompensationRecord> {

    @Override
    public CompensationRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        CompensationRecord compensationRecord = new CompensationRecord();
        compensationRecord.setRecordTime(rs.getTimestamp("recordTime"));
        compensationRecord.setAge(rs.getString("age"));
        compensationRecord.setIndustry(rs.getString("industry"));
        compensationRecord.setJobTitle(rs.getString("jobTitle"));
        compensationRecord.setAnnualSalary(rs.getString("annualSalary"));
        compensationRecord.setCurrency(rs.getString("currency"));
        compensationRecord.setLocation(rs.getString("location"));
        compensationRecord.setPostGradWrkExperience(rs.getString("postGradWrkExperience"));
        compensationRecord.setAdditionalWrkInfo(rs.getString("additionalWrkInfo"));
        compensationRecord.setOther(rs.getString("other"));
        return compensationRecord;
    }
}
