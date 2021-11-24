package tn.socotu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.service.AdherentService;
import tn.socotu.spring.service.ChartService;

@RestController
@RequestMapping("/api")

public class ChartController {
	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	AdherentService adherentService;
	
	@Autowired
	ChartService chartService;
	//  http://localhost:8080/select_chart_byville
	@GetMapping("/select_chart_byville")
	public   List<Adherent> select_chart_byville()
	{
		
		   return chartService.select_chart_byville();
	}
	
	//  http://localhost:8080/select_chart_bytypeadh
	@GetMapping("/select_chart_bytypeadh")
	public   List<Adherent> select_chart_bytypeadh()
	{
		
		   return chartService.select_chart_bytypeadh();
	}
	
	//  http://localhost:8080/select_chart_remboursement_byannee
	@GetMapping("/select_chart_remboursement_byannee")
	public   List<Remboursement> select_chart_remboursement_byannee()
	{
		
		   return chartService.select_chart_remboursement_byannee();
	}
	



}
