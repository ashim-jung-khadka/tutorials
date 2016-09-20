package com.github.ashim.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.mapper.SampleMapper;
import com.github.ashim.model.Sample;

@Repository
@Transactional
public class SampleDaoImpl implements SampleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sample> getSamples() {
		List<Sample> samples = jdbcTemplate.query("select * from sample", new SampleMapper());

		return samples;
	}

}