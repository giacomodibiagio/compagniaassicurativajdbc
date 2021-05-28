package it.eserciziocompagniaassicurativa.test;

import java.sql.Date;

import it.eserciziocompagniaassicurativa.model.*;
import it.eserciziocompagniaassicurativa.service.MyServiceFactory;
import it.eserciziocompagniaassicurativa.service.automobile.IAutomobileService;
import it.eserciziocompagniaassicurativa.service.compagniaassicurativa.ICompagniaAssicurativaService;
import it.eserciziocompagniaassicurativa.service.proprietario.IProprietarioService;

public class TestCompagniaAssicurativa {

	public static void main(String[] args) {
		ICompagniaAssicurativaService compagniaAssicurativaService = MyServiceFactory.getCompagniaAssicurativaImpl();
		IProprietarioService proprietarioService = MyServiceFactory.getProprietarioImpl();
		IAutomobileService automobileService = MyServiceFactory.getAutomobileImpl();
		
		CompagniaAssicurativa comp1 = new CompagniaAssicurativa();
		comp1.setIdCompagniaAssicurativa((long) 1);
		comp1.setRagioneSociale("compagnia1");
		comp1.setIndirizzo("Via Roma 98,Milano");
		comp1.setDataFondazione(new java.sql.Date(2021-1900,4,11));
		
		Proprietario proprietario1 = new Proprietario();
		proprietario1.setIdProprietario((long)1);
		proprietario1.setNome("Alessandro");
		proprietario1.setCognome("Zamboni");
		proprietario1.setDataNascita(new java.sql.Date(1996-1900,6,28));
		
		try {
			/*compagniaAssicurativaService.inserisciNuovo(comp1);
			proprietarioService.inserisciNuovo(proprietario1);*/
			
		/*	Automobile auto1 = new Automobile();
			auto1.setIdAutomobile((long)3);
			auto1.setMarca("Fiat");
			auto1.setModello("Punto");
			auto1.setCilindrata(345);
			auto1.setDataImmatricolazione(new java.sql.Date(2012-1900,2,23));
			comp1.setIdCompagniaAssicurativa((long)2);
			proprietario1.setIdProprietario((long)2);
			auto1.setCompagniaAssicurativa(comp1);
			auto1.setProprietario(proprietario1);
			automobileService.inserisciNuovo(auto1);*/
			
			//compagniaAssicurativaService.rimuovi(comp1);
			//proprietarioService.rimuovi(proprietario1);
			//automobileService.rimuovi(auto1);
			
			//automobileService.getAutomobiliWhereProprietarioPiuDi40anni();
			//proprietarioService.getProprietariWhereAutomobileHaCompagniaAMilano();
			//proprietarioService.getProprietariWhereCompagniaMenoDiVentiAutomobili();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
