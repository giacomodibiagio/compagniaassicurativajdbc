package it.eserciziocompagniaassicurativa.dao.proprietario;


import java.util.ArrayList;

import it.eserciziocompagniaassicurativa.dao.IBaseDAO;
import it.eserciziocompagniaassicurativa.model.Proprietario;


public interface IProprietarioDAO extends IBaseDAO<Proprietario> {

	void riempiListaAutomobili(Proprietario input) throws Exception;

	ArrayList<Proprietario> getProprietariWhereAutomobileHaCompagniaAMilano() throws Exception;

	ArrayList<Proprietario> getProprietariWhereCompagniaMenoDiVentiAutomobili() throws Exception;

}
