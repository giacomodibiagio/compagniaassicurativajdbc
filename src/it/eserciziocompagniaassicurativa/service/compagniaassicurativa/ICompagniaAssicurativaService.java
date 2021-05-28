package it.eserciziocompagniaassicurativa.service.compagniaassicurativa;

import java.util.List;

import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.dao.compagniaassicurativa.ICompagniaAssicurativaDAO;
import it.eserciziocompagniaassicurativa.model.CompagniaAssicurativa;


public interface ICompagniaAssicurativaService {
	// questo mi serve per injection
	public void setCompagniaAssicurativaDao(ICompagniaAssicurativaDAO compagniaAssicurativaDao);
	
	public void setAutomobileDao(IAutomobileDAO automobileDao);
		
	public List<CompagniaAssicurativa> listAll() throws Exception;
		
	public CompagniaAssicurativa findById(Long idInput) throws Exception;
		
	public int aggiorna(CompagniaAssicurativa input) throws Exception;
		
	public int inserisciNuovo(CompagniaAssicurativa input) throws Exception;
		
	public int rimuovi(CompagniaAssicurativa input) throws Exception;
		
	public List<CompagniaAssicurativa> findByExample(CompagniaAssicurativa input) throws Exception;

	void riempiListaAutomobili(CompagniaAssicurativa input) throws Exception;	
}
