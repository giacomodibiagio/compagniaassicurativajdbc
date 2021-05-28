package it.eserciziocompagniaassicurativa.dao.automobile;

import java.util.ArrayList;

import it.eserciziocompagniaassicurativa.dao.IBaseDAO;
import it.eserciziocompagniaassicurativa.model.Automobile;


public interface IAutomobileDAO extends IBaseDAO<Automobile>{

	ArrayList<Automobile> getAutomobiliWhereProprietarioPiuDi40anni() throws Exception;

}
