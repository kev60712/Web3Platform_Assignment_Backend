package com.example.backend_assignment.Model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  CompensationRecord {

    @JsonProperty("Timestamp")
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private Timestamp recordTime;
    @JsonProperty("How old are you?")
    private String age;
    @JsonProperty("What industry do you work in?")
    private String industry;
    @JsonProperty("Job title")
    private String jobTitle;
    @JsonProperty("What is your annual salary?")
    private String annualSalary;
    @JsonProperty("Please indicate the currency")
    private String currency;
    @JsonProperty("Where are you located? (City/state/country)")
    private String location;
    @JsonProperty("How many years of post-college professional work experience do you have?")
    private String postGradWrkExperience;
    @JsonProperty("If your job title needs additional context, please clarify here:")
    private String additionalWrkInfo;
    @JsonProperty("If \"Other,\" please indicate the currency here:")
    private String other;

    public CompensationRecord(){}

    public CompensationRecord(String age, String industry, String currency) {
        this.age = age;
        this.industry = industry;
        this.currency = currency;
    }
}