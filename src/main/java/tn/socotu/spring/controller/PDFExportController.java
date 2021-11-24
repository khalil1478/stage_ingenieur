package tn.socotu.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

/*import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;*/

//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;*/
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

//import com.itextpdf.text.DocumentException;

import tn.socotu.spring.service.PDFGeneratorService;

@Controller
public class PDFExportController {
	
	@Autowired
	PDFGeneratorService 	 pdfGeneratorService;
	
	
	//http://localhost:8080/generate_pdf
/*	 @GetMapping("/generate_pdf_remb")
	    public void generatePDF(HttpServletResponse response) throws IOException, DocumentException {	
	    response.setContentType("application/pdf");
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	 String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	  response.setHeader(headerKey, headerValue);

	        pdfGeneratorService.export(response);
	    }*/
	 

//	http://localhost:8080/report/pdf
	/*@GetMapping("/report/pdf")
	 public String generateReport () throws FileNotFoundException, JRException  {
		return pdfGeneratorService.exportReport();
	 }*/
	
	
	
	//http://localhost:8080/generate_pdf
	@GetMapping("/generate_pdf")
	    public void generatePDF(HttpServletResponse response) throws IOException, DocumentException  {	
	    response.setContentType("application/pdf");
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	 String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	  response.setHeader(headerKey, headerValue);

	         pdfGeneratorService.export(response);
	    }
	 

//	http://localhost:8080/report/pdf
	/*@GetMapping("/report/pdf")
	 public String generateReport () throws FileNotFoundException, JRException  {
		return pdfGeneratorService.exportReport();
	 }*/

}
