package tn.socotu.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.EnregistrementRepository;
import tn.socotu.spring.repository.RemboursementRepository;


@Service
public class GmailService {
	 @Autowired
	 JavaMailSender javaMailSender;
	 @Autowired
	 EnregistrementRepository enregistrementRepository;
	 @Autowired
		RemboursementRepository remboursementRepository;
	 @Autowired
	 GmailService gmailService;
		
		@Autowired
		AdherentRepository  adherentRepository;
/*	@Autowired
      JavaMailSender javaMailSender;*/



	 public String sendSimpleEmail(String toEmail,
	                                String body,
	                                String subject)  {
	        SimpleMailMessage message = new SimpleMailMessage();

	        message.setFrom("socotutest@gmail.com");
	        message.setTo(toEmail);
	        message.setText(body);
	        message.setSubject(subject);
	        javaMailSender.send(message);
	      //  System.out.println("Mail Send...");		 
	        return "mail send";
	    }
	 
	 
	 
	/* private JavaMailSender javaMailSender;

	    @Autowired
	    public GmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    @Async
	    public void sendEmail(SimpleMailMessage email) {
	        javaMailSender.send(email);
	    }*/


		/*public String sendEmail() {
			// TODO Auto-generated method stub
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("socotutest@gmail.com");
			message.setTo("socotutest@gmail.com");
			message.setSubject("Test Subject");
			message.setText("Test Body");
			
			javaMailSender.send(message);
			
			return "Mail sent successfully";		}*/
	
	public  void sendEmailWithAttachment(String file_name) throws MessagingException, IOException {
		

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        helper.setTo("socotutest@gmail.com");
	        helper.setSubject("les attestations");
	        // default = text/plain
	        //helper.setText("Check attachment for image!");
	        
	        // true = text/html
	        helper.setText(" Boujour , veuillez trouvez ci-joint  <h3>list des enfants</h3>", true);

			// hard coded a file path
	        FileSystemResource file = new FileSystemResource(new File("C:/Users\\khalil\\Downloads\\"+file_name));

	        helper.addAttachment(file_name, file);

	        javaMailSender.send(msg);
	    	int year_current = Calendar.getInstance().get(Calendar.YEAR);
			List<Enregistrement> list = new ArrayList<>();
			for (Adherent adh : enregistrementRepository.select_enfant_bydatecurrent_file_notnull(year_current)) {
				Enregistrement enregistrement = enregistrementRepository.select_enregistement_byadh_bydate(adh.getId(), year_current);		  	
				list.add(enregistrement)	;	
			}			
			 for (Enregistrement cc : list ) {	        	
		        	cc.setStatus(cc.getStatus()+1);	        	
		 		   enregistrementRepository.save(cc);
				}

	}
	
	
	public  void envoyer_email_byadherent_byenregistrement(int idadh) throws MessagingException, IOException {
	  	int year_current = Calendar.getInstance().get(Calendar.YEAR);
			List<Enregistrement> list = new ArrayList<>();
			Adherent adh=  adherentRepository.findById(idadh).get() ;
				Enregistrement enregistrement = enregistrementRepository.select_enregistement_byadh_bydate(adh.getId(), year_current);		  	
			
			
			
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("socotutest@gmail.com");

        helper.setSubject("les attestations");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(" Boujour , veuillez trouvez ci-joint  l'attestation de l'enfant  "+adh.getNom() +" " + adh.getPrenom()+"  ,et du matricule  "+ adh.getMatricule()  , true);

		
		// hard coded a file path
        FileSystemResource file = new FileSystemResource(new File("C:/Users\\khalil\\Documents\\uploads\\"+enregistrement.getFile()));

        helper.addAttachment(enregistrement.getFile(), file);

        javaMailSender.send(msg);

  



}



	public void envoyer_email_byadherent_byremboursement(int idremboursement) {
		// TODO Auto-generated method stub
		Remboursement r =remboursementRepository.findById(idremboursement).get();
		
			gmailService.sendSimpleEmail("socotutest@gmail.com",
		
				"Bonjour"
				+ " Mr/Mme  " + r.getAdherent().getNom() +" son matricule est  " + r.getAdherent().getMatricule() + " "+r.getAdherent().getNom()+ " a une faute de montant de remboursement   du bordereau n* "+ r.getBordereau().getId()+
				"  du  numero bulletin est "+ r.getNumero_bulletin() +"  du category est  " + r.getCategory().getNom()+" le montant de remboursement est " + r.getMontant_remboursement() +" qui a un ecart  de "+ r.getEcart(), 
				
				"reclamation");
		

	}

	
	
	
	
	
	
	
	
	
	
}
