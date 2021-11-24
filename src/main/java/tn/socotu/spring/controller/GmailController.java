package tn.socotu.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.repository.RemboursementRepository;
import tn.socotu.spring.service.GmailService;
import tn.socotu.spring.service.RemboursementService;

@RestController
public class GmailController {

	@Autowired
	 GmailService gmailService;
	@Autowired
	RemboursementRepository   remboursementRepository ;
	
	@Autowired
	RemboursementService remboursementService;

	//  http://localhost:8080/sendMail
	@GetMapping("/sendMail")
	public void sendMail()     {

		System.out.println("khalil boutar");
		
		gmailService.sendSimpleEmail("socotutest@gmail.com", "bonjour mohamed khalil boutar", "test");
		
	}
	
	@GetMapping("/sendEmailWithAttachment/{cc}")
	public String sendEmailWithAttachment(@PathVariable("cc") String cc) throws AddressException, MessagingException, IOException {
	   //sendEmail();
		System.out.println("*******************sendEmailWithAttachmentcc*******************"+cc);
		gmailService.sendEmailWithAttachment(cc);
	   return "Email sent successfully";   
	}
	
	
	
	@GetMapping("/envoyer_email_byadherent_byenregistrement/{idadh}")
	public String envoyer_email_byadherent_byenregistrement(@PathVariable("idadh") int idadh) throws AddressException, MessagingException, IOException {
	   //sendEmail();
		System.out.println("*******************envoyer_email_byadherent_byenregistrement*******************"+idadh);
		gmailService.envoyer_email_byadherent_byenregistrement(idadh);
	   return "Email sent successfully";   
	}
	
	
	
	
	// http://localhost:8080/modifier_ecart_byremboursement/{id_reboursement}
	@PutMapping("/modifier_ecart_byremboursement/{id_reboursement}/{ecart}/{type}")
	public void modifier_ecart_byremboursement(@PathVariable("id_reboursement") int id_reboursement,@PathVariable("ecart") String ecart,@PathVariable("type") String type) {
		remboursementService.modifier_ecart_byremboursement(id_reboursement,ecart,type);
	}
	
	
	
	
	@GetMapping("/envoyer_email_byadherent_byremboursement/{idremboursement}")
	public String envoyer_email_byadherent_byremboursement(@PathVariable("idremboursement") int idremboursement) throws AddressException, MessagingException, IOException {
	   //sendEmail();
		System.out.println("*******************envoyer_email_byadherent_byenregistrement*******************"+idremboursement);
		gmailService.envoyer_email_byadherent_byremboursement(idremboursement);
	   return "Email sent successfully";   
	}
	
	


}
