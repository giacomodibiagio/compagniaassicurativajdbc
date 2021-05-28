package it.eserciziocompagniaassicurativa.service.automobile;

import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.model.Automobile;


public interface IAutomobileService {
	// questo mi serve per injection
	public void setAutomobileoDao(IAutomobileDAO automobileDao);
		
	public List<Automobile> listAll() throws Exception;
		
	public Automobile findById(Long idInput) throws Exception;
		
	public int aggiorna(Automobile input) throws Exception;
		
	public int inserisciNuovo(Automobile input) throws Exception;
		
	public int rimuovi(Automobile input) throws Exception;
		
	public List<Automobile> findByExample(Automobile input) throws Exception;

	ArrayList<Automobile> getAutomobiliWhereProprietarioPiuDi40anni() throws Exception;
}
