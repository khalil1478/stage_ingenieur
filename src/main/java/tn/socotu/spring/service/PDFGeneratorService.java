package tn.socotu.spring.service;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/*import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;*/

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/*import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;*/

@Service
public class PDFGeneratorService {
	
	/*  public void export(HttpServletResponse response) throws DocumentException, IOException  {
			
		//	  String path = "C:\\Users\\Documents\\pdfsocotu";
	      //  Document document = new Document();
	  // PdfWriter.getInstance(document, new  FileOutputStream(path));
	   
	   Document document = new Document(PageSize.A4);
		
			PdfWriter.getInstance(document, response.getOutputStream());
		
	

	   document.open();
     Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
     //  fontTitle.setSize(18);

       Paragraph paragraph = new Paragraph("This is a title.");
       paragraph.setAlignment(Paragraph.ALIGN_CENTER);

       Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
       fontParagraph.setSize(12);

       Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
       paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
       document.add(paragraph);
       document.add(paragraph2);
       document.close();
		
       
 
		 
	    }*/
	
	
	
	/*public String exportReport() throws FileNotFoundException, JRException {
		 
	        String path = "C:\\Users\\khalil\\Documents\\pdfsocotu";
	        
	    //    Optional<UserInformation> user = userRepo.findById(id);
//	        String nom = employee.get().getNom();
//	        String prenom = employee.get().getPrenom();
//	        List<Object> data = new ArrayList<>();
//	        data.add(nom);
//	        data.add(prenom);
//	        
	        Map<String, Object> parameters = new HashMap<String, Object>();
         parameters.put("prenom","fksdhflk");
         parameters.put("nom", "user.get().getNom()");
      
     
         
         
	        
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:user.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
	        
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        
	        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//user.pdf");
	        

	        return "report generated in path : " + path;
	    }*/
	
	public void export(HttpServletResponse response)  throws  IOException, DocumentException   {
		
	
	   
	   Document document = new Document(PageSize.A4);
		
			PdfWriter.getInstance(document, response.getOutputStream());
		
	

	   document.open();
     Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fontTitle.setSize(18);

       Paragraph paragraph = new Paragraph("This is a title.");
       paragraph.setAlignment(Paragraph.ALIGN_CENTER);

       Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
       fontParagraph.setSize(12);

       Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
       paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
       document.add(paragraph);
       document.add(paragraph2);
       document.close();
	
		 
	    }

	 
	  
	
	

}