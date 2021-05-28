package it.eserciziocompagniaassicurativa.service;

import it.eserciziocompagniaassicurativa.dao.automobile.AutomobileDAOImpl;
import it.eserciziocompagniaassicurativa.dao.compagniaassicurativa.CompagniaAssicurativaDAOImpl;
import it.eserciziocompagniaassicurativa.dao.proprietario.ProprietarioDAOImpl;
import it.eserciziocompagniaassicurativa.service.automobile.AutomobileServiceImpl;
import it.eserciziocompagniaassicurativa.service.automobile.IAutomobileService;
import it.eserciziocompagniaassicurativa.service.compagniaassicurativa.CompagniaAssicurativaServiceImpl;
import it.eserciziocompagniaassicurativa.service.compagniaassicurativa.ICompagniaAssicurativaService;
import it.eserciziocompagniaassicurativa.service.proprietario.IProprietarioService;
import it.eserciziocompagniaassicurativa.service.proprietario.ProprietarioServiceImpl;


public class MyServiceFactory {
	public static ICompagniaAssicurativaService getCompagniaAssicurativaImpl() {
		ICompagniaAssicurativaService compagniaAssicurativaService = new CompagniaAssicurativaServiceImpl();
		compagniaAssicurativaService.setCompagniaAssicurativaDao(new CompagniaAssicurativaDAOImpl());
		compagniaAssicurativaService.setAutomobileDao(new AutomobileDAOImpl());
		
		return compagniaAssicurativaService;
	}
	
	public static IProprietarioService getProprietarioImpl() {
		IProprietarioService proprietarioService = new ProprietarioServiceImpl();
		proprietarioService.setProprietarioDao(new ProprietarioDAOImpl());
		proprietarioService.setAutomobileDao(new AutomobileDAOImpl());		
	
		return proprietarioService;
	}
	
	public static IAutomobileService getAutomobileImpl() {
		IAutomobileService automobileService = new AutomobileServiceImpl();		
		automobileService.setAutomobileoDao(new AutomobileDAOImpl());
		return automobileService;
	}
}
