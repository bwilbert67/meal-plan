package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcMeasurementDao implements MeasurementDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMeasurementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getMeasurementIdByUnit (String unit) {
        String sql = "SELECT measurement_id FROM measurement WHERE measurement_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, unit);
    }


}
