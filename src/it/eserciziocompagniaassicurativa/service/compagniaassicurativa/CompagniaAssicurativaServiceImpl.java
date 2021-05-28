package it.eserciziocompagniaassicurativa.service.compagniaassicurativa;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.connection.MyConnection;
import it.eserciziocompagniaassicurativa.dao.Constants;
import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.dao.compagniaassicurativa.ICompagniaAssicurativaDAO;
import it.eserciziocompagniaassicurativa.model.CompagniaAssicurativa;


public class CompagniaAssicurativaServiceImpl implements ICompagniaAssicurativaService{
	private ICompagniaAssicurativaDAO compagniaAssicurativaDao;
	private IAutomobileDAO automobileDao;
	
	@Override
	public void setCompagniaAssicurativaDao(ICompagniaAssicurativaDAO compagniaAssicurativaDao) {
		this.compagniaAssicurativaDao = compagniaAssicurativaDao;
	}

	@Override
	public void setAutomobileDao(IAutomobileDAO automobileDao) {
		this.automobileDao = automobileDao;
	}
	
	@Override
	public List<CompagniaAssicurativa> listAll() throws Exception {
		List<CompagniaAssicurativa> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = compagniaAssicurativaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public CompagniaAssicurativa findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		CompagniaAssicurativa result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = compagniaAssicurativaDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(CompagniaAssicurativa input) throws Exception {
		if (input == null || input.getIdCompagniaAssicurativa() == null || input.getIdCompagniaAssicurativa() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = compagniaAssicurativaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(CompagniaAssicurativa input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");


		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = compagniaAssicurativaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(CompagniaAssicurativa input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);
			automobileDao.setConnection(connection);

			System.out.println("compagnia del quale sto per fare la lista di automobili    "+input);
		
			this.compagniaAssicurativaDao.riempiListaAutomobili(input);			
			
			System.out.println(input.getAutomobili().size());
			
			if(input.getAutomobili().size() > 0) {
				System.out.println("La compagnia possiede automobili quindi non la elimino ed esco");
				throw new Exception("La compagnia ha delle automobili!");
			}
	
			// eseguo quello che realmente devo fare
			compagniaAssicurativaDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return 0;
	}

	@Override
	public List<CompagniaAssicurativa> findByExample(CompagniaAssicurativa input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		List<CompagniaAssicurativa> result = new ArrayList<>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = compagniaAssicurativaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public void riempiListaAutomobili(CompagniaAssicurativa input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			compagniaAssicurativaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			compagniaAssicurativaDao.riempiListaAutomobili(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	

}
