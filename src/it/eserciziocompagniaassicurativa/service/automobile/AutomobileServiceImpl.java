package it.eserciziocompagniaassicurativa.service.automobile;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.connection.MyConnection;
import it.eserciziocompagniaassicurativa.dao.Constants;
import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.model.Automobile;





public class AutomobileServiceImpl implements IAutomobileService {
	private IAutomobileDAO automobileDao;
	@Override
	public void setAutomobileoDao(IAutomobileDAO automobileDao) {
		this.automobileDao = automobileDao;
	}

	@Override
	public List<Automobile> listAll() throws Exception {
		List<Automobile> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = automobileDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Automobile findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Automobile result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = automobileDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Automobile input) throws Exception {
		if (input == null || input.getIdAutomobile() == null || input.getIdAutomobile() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = automobileDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Automobile input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		if (input.getCompagniaAssicurativa() == null)
			throw new Exception("Valore di input non ammesso.");
		
		if (input.getProprietario() == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);


			// eseguo quello che realmente devo fare
			result = automobileDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Automobile input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			automobileDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return 0;
	}

	@Override
	public ArrayList<Automobile> getAutomobiliWhereProprietarioPiuDi40anni() throws Exception {
		ArrayList<Automobile> result = new ArrayList<>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = automobileDao.getAutomobiliWhereProprietarioPiuDi40anni();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		for(Automobile automobileItem: result)
			System.out.println(automobileItem);
		
		return result;
	}
	
	@Override
	public List<Automobile> findByExample(Automobile input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		List<Automobile> result = new ArrayList<>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			automobileDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = automobileDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

}
