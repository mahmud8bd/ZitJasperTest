package com.zit.jasper.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

public interface JasperReportService {
	void makePdfReport(HttpServletResponse httpServletResponse) throws JRException, IOException;
}
