package com.loma.kafka.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : Akash-loma
 * @project: kafka-CMS
 * @mailto : akash.singhal@lomatechnology.com
 * @created : 2024/8/14
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Case {
    private String id;
    private String vendor_code;
    private String case_id;
    private String requested_time;
    private String case_type_id;
    private String type_name;
    private Fields fields;

    // Getters and Setters

    public String getVendor_code() {
        return vendor_code;
    }

    public void setVendor_code(String vendor_code) {
        this.vendor_code = vendor_code;
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getRequested_time() {
        return requested_time;
    }

    public void setRequested_time(String requested_time) {
        this.requested_time = requested_time;
    }

    public String getCase_type_id() {
        return case_type_id;
    }

    public void setCase_type_id(String case_type_id) {
        this.case_type_id = case_type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Nested Fields class

    public static class Fields {
        private String first_name;
        private String last_name;
        private String amount;

        // Getters and Setters

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

}