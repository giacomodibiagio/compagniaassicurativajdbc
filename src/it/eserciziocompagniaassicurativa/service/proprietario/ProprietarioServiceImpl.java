package it.eserciziocompagniaassicurativa.service.proprietario;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.connection.MyConnection;
import it.eserciziocompagniaassicurativa.dao.Constants;
import it.eserciziocompagniaassicurativa.dao.automobile.IAutomobileDAO;
import it.eserciziocompagniaassicurativa.dao.proprietario.IProprietarioDAO;
import it.eserciziocompagniaassicurativa.model.Automobile;
import it.eserciziocompagniaassicurativa.model.Proprietario;

public class ProprietarioServiceImpl implements IProprietarioService{
	private IProprietarioDAO proprietarioDao;
	private IAutomobileDAO automobileDao;
	
	@Override
	public void setProprietarioDao(IProprietarioDAO proprietarioDao) {
		this.proprietarioDao = proprietarioDao;		
	}
	
	@Override
	public void setAutomobileDao(IAutomobileDAO automobileDao) {
		this.automobileDao = automobileDao;             
	}

	@Override
	public List<Proprietario> listAll() throws Exception {
		List<Proprietario> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Proprietario findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Proprietario result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Proprietario input) throws Exception {
		if (input == null || input.getIdProprietario() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Proprietario input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");


		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Proprietario input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);
			automobileDao.setConnection(connection);

			System.out.println("proprietario del quale sto per fare la lista "+input);
		
			this.proprietarioDao.riempiListaAutomobili(input);			
			
			if (input.getAutomobili().size() > 0) {
				for (Automobile automobileItem : input.getAutomobili()) {
					System.out.println("STO RIMUOVENDO LE AUTOMOBILI DEL PROPRIETARIO PRESO IN ESAME ");
					automobileDao.delete(automobileItem);
				}
					
			}

			// eseguo quello che realmente devo fare
			proprietarioDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return 0;
	}

	@Override
	public List<Proprietario> findByExample(Proprietario input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		List<Proprietario> result = new ArrayList<>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	@Override
	public void riempiListaAutomobili(Proprietario input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			proprietarioDao.riempiListaAutomobili(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ArrayList<Proprietario> getProprietariWhereAutomobileHaCompagniaAMilano() throws Exception {
		
		ArrayList<Proprietario> result = new ArrayList<>();
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.getProprietariWhereAutomobileHaCompagniaAMilano();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		for(Proprietario proprietarioItem: result)
			System.out.println(proprietarioItem);
		
		return result;
	}
	
	@Override
	public ArrayList<Proprietario> getProprietariWhereCompagniaMenoDiVentiAutomobili() throws Exception {
		ArrayList<Proprietario> result = new ArrayList<>();
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			proprietarioDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = proprietarioDao.getProprietariWhereCompagniaMenoDiVentiAutomobili();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		for(Proprietario proprietarioItem: result)
			System.out.println(proprietarioItem);
		
		return result;
	}
}
