package com.github.ashim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.ashim.bean.DataBean;
import com.github.ashim.service.ChartService;

@Controller
public class HighChartsController {

	@Autowired
	ChartService chartService;

	@RequestMapping({ "/", "/index" })
	public String showCharts() {
		return "index";
	}

	@RequestMapping({ "/linechart1" })
	@ResponseBody
	public DataBean showLineChart1() {
		return chartService.getLineChartData1();
	}

	@RequestMapping({ "/linechart2" })
	@ResponseBody
	public DataBean showLineChart2() {
		return chartService.getLineChartData2();
	}

	@RequestMapping({ "/linechart3" })
	@ResponseBody
	public DataBean showLineChart3() {
		return chartService.getLineChartData3();
	}

}
