package com.github.ashim.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.ashim.model.Sample;

public class SampleMapper implements RowMapper<Sample> {

	@Override
	public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sample sample = new Sample();
		sample.setId(rs.getInt("id"));
		sample.setName(rs.getString("name"));
		return sample;
	}

}