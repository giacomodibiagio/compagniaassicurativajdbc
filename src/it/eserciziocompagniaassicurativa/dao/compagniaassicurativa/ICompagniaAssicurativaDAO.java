package it.eserciziocompagniaassicurativa.dao.compagniaassicurativa;




import it.eserciziocompagniaassicurativa.dao.IBaseDAO;
import it.eserciziocompagniaassicurativa.model.CompagniaAssicurativa;


public interface ICompagniaAssicurativaDAO  extends IBaseDAO<CompagniaAssicurativa> {

	void riempiListaAutomobili(CompagniaAssicurativa input) throws Exception;

}
