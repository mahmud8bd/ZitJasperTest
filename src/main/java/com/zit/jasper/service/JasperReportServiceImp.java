package com.zit.jasper.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.zit.jasper.entity.Vat;
import com.zit.jasper.repository.VatRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportServiceImp implements JasperReportService{

	@Autowired
	private VatRepository vatRepository;

	@Override
	public void makePdfReport(HttpServletResponse httpServletResponse) throws JRException, IOException {
		try {
          List<Vat> vat = vatRepository.findAll();
          HashMap<String, Object> parameter = new HashMap<>();
          parameter.put("musok", "মুসক-৬.৩");
          parameter.put("note1", "* ১। এস আর  ও নং  ১৮৬ -আইন/২০১৯/৪৩-মুসক তারিখ  ১৩ জুন,  ২০১৯ এর ব্যাখ্যা মূলে  মুষক   পরিশোধিত।");
          parameter.put("note2", "* ২। উক্তপণ্য চালানের বিপরীতে  প্রদেয়  কর কেন্দ্রীয় নিবন্ধন নং-৮৬৭৮৬৮৭  হইতে পরিশোধযোগ্য।");
          parameter.put("h", vat);
          
          
          // Get file and compile it
          File file = ResourceUtils.getFile("classpath:jasperreports/Vat.jrxml");
          JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
          JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vat);
          
         
          httpServletResponse.setContentType("application/pdf");
          DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
          String currentDateTime = dateFormatter.format(new Date());
          
          String headerKey = "Content-Disposition";
          String headerValue = "attachment; filename=Zit-" + currentDateTime + ".pdf";
          httpServletResponse.setHeader(headerKey, headerValue);
          
          // Fill Jasper report
          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
          
          // Export report to the response output stream
          JasperExportManager.exportReportToPdfStream(jasperPrint, httpServletResponse.getOutputStream());
      } catch (Exception e) {
          // Handle exceptions appropriately
          e.printStackTrace();
          // Set an error response
          httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }
	}
}
