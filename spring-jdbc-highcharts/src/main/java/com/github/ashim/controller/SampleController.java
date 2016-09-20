package com.github.ashim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.ashim.jdbc.SampleDao;
import com.github.ashim.model.Sample;

@Controller
public class SampleController {

	@Autowired
	private SampleDao sampleDao;

	@ResponseBody
	@RequestMapping("/samples")
	public List<Sample> getSampleList() {
		return sampleDao.getSamples();
	}

}