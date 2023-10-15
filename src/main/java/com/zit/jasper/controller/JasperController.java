package com.zit.jasper.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zit.jasper.service.JasperReportServiceImp;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin(origins = "*")
public class JasperController {
	@Autowired
	private JasperReportServiceImp jasperService;

	@GetMapping("/generate-pdf")
	public void makePdfReport(HttpServletResponse httpServletResponse)throws IOException, JRException {
		jasperService.makePdfReport(httpServletResponse);
	}

}
