package it.eserciziocompagniaassicurativa.service.proprietario;

import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.dao.proprietario.IProprietarioDAO;
import it.eserciziocompagniaassicurativa.model.Proprietario;



public interface IProprietarioService {
	// questo mi serve per injection
	public void setProprietarioDao(IProprietarioDAO proprietarioDao);
	
	public void setAutomobileDao(IAutomobileDAO automobileDao);
	
	public List<Proprietario> listAll() throws Exception;
	
	public Proprietario findById(Long idInput) throws Exception;
	
	public int aggiorna(Proprietario input) throws Exception;
	
	public int inserisciNuovo(Proprietario input) throws Exception;
	
	public int rimuovi(Proprietario input) throws Exception;
	
	public List<Proprietario> findByExample(Proprietario input) throws Exception;

	void riempiListaAutomobili(Proprietario input) throws Exception;

	ArrayList<Proprietario> getProprietariWhereAutomobileHaCompagniaAMilano() throws Exception;

	ArrayList<Proprietario> getProprietariWhereCompagniaMenoDiVentiAutomobili() throws Exception;
}
