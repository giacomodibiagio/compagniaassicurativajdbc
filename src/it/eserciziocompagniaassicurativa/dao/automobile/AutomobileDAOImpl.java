package it.eserciziocompagniaassicurativa.dao.automobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.eserciziocompagniaassicurativa.dao.AbstractMySQLDAO;
import it.eserciziocompagniaassicurativa.model.Automobile;
import it.eserciziocompagniaassicurativa.model.CompagniaAssicurativa;
import it.eserciziocompagniaassicurativa.model.Proprietario;



public class AutomobileDAOImpl extends AbstractMySQLDAO implements IAutomobileDAO{

	@Override
	public List<Automobile> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Automobile> result = new ArrayList<Automobile>();
		Automobile temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * \r\n"
				+ "from automobile a "
				+ "inner join compagnia_assicurativa c on a.fk_compagnia_assicurativa = c.id_compagnia_assicurativa\r\n"
				+ "inner join proprietario p on a.fk_proprietario = p.id_proprietario;")) {

			while (rs.next()) {
				temp = new Automobile();

				temp.setIdAutomobile(rs.getLong("id_automobile"));				
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setCilindrata(rs.getInt("cilindrata"));
				temp.setDataImmatricolazione(rs.getDate("data_immatricolazione"));	
				temp.setCompagniaAssicurativa(new CompagniaAssicurativa(
											rs.getInt("id_compagnia_assicurativa"),
											rs.getString("ragione_sociale"),
											rs.getString("indirizzo"),
											rs.getDate("data_fondazione")));								
				temp.setProprietario(new Proprietario(
						rs.getLong("id_proprietario"),
						rs.getString("nome"),
						rs.getString("cognome"),
						rs.getDate("data_nascita")));
				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public Automobile get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Automobile result = new Automobile();
		
		try (PreparedStatement ps = connection.prepareStatement("select * \r\n"
				+ "from automobile a\r\n"
				+ "inner join compagnia_assicurativa c on a.fk_compagnia_assicurativa = c.id_compagnia_assicurativa\r\n"
				+ "inner join proprietario p on a.fk_proprietario = p.id_proprietario\r\n"
				+ "where id_automobile = ?;")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					//result = new CD();
				
					result.setIdAutomobile(rs.getLong("id_automobile"));
					result.setMarca(rs.getString("marca"));
					result.setModello(rs.getString("modello"));	
					result.setCilindrata(rs.getInt("cilindrata"));
					result.setDataImmatricolazione(rs.getDate("data_immatricolazione"));
					result.setCompagniaAssicurativa(new CompagniaAssicurativa(
							rs.getInt("id_compagnia_assicurativa"),
							rs.getString("ragione_sociale"),
							rs.getString("indirizzo"),
							rs.getDate("data_fondazione")));								
					result.setProprietario(new Proprietario(
							rs.getLong("id_proprietario"),
							rs.getString("nome"),
							rs.getString("cognome"),
							rs.getDate("data_nascita")));		
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public int update(Automobile input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdAutomobile() == null || input.getIdAutomobile() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("UPDATE cd SET marca=?, modello=?, cilindrata=?, fk_id_compagnia_assicurativa, fk_id_proprietario = ? where id_automobile=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getCilindrata());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setLong(4, input.getCompagniaAssicurativa().getIdCompagniaAssicurativa());
			ps.setLong(5, input.getProprietario().getIdProprietario());
			ps.setLong(6, input.getIdAutomobile());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public int insert(Automobile input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		if(input.getCompagniaAssicurativa().getIdCompagniaAssicurativa() == null)
			throw new Exception("Valore di input non ammesso.");
		
		if(input.getProprietario().getIdProprietario() == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO automobile (marca,modello,cilindrata,data_immatricolazione,fk_compagnia_assicurativa,fk_proprietario) VALUES (?, ?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getCilindrata());
			ps.setDate(4, input.getDataImmatricolazione());		
			ps.setLong(5, input.getCompagniaAssicurativa().getIdCompagniaAssicurativa());
			ps.setLong(6, input.getProprietario().getIdProprietario());
	
			// quando si fa il setDate serve un tipo java.sql.Date
	
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public int delete(Automobile input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdAutomobile() == null || input.getIdAutomobile() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM automobile WHERE id_automobile=?")) {
			ps.setLong(1, input.getIdAutomobile());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Automobile> findByExample(Automobile input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Automobile> result = new ArrayList<Automobile>();
		Automobile automobileTemp = null;

		String query = "select * from automobile where 1=1 ";
		
		if (input.getMarca() != null && !input.getMarca().equals("")) {
			query += " and marca ='" + input.getMarca() + "' ";
		}
		
		if (input.getModello() != null && !input.getModello().equals("")) {
			query += " and nomodello ='" + input.getModello() + "' ";
		}
		
		if (input.getCilindrata() != null && !(input.getCilindrata() < 0)) {
			query += " and cilindrata = " + input.getCilindrata() + " ";
		}

		if(input.getDataImmatricolazione() != null)
			query += " and data_immatricolazione ='" + input.getDataImmatricolazione().getTime() + "' ";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				automobileTemp = new Automobile();
				automobileTemp.setMarca(rs.getString("marca"));
				automobileTemp.setModello(rs.getString("modello"));
				automobileTemp.setCilindrata(rs.getInt("cilindrata"));
				automobileTemp.setDataImmatricolazione(rs.getDate("data_immatricolazione"));
				automobileTemp.setIdAutomobile(rs.getLong("id_automobile"));

				result.add(automobileTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		return result;
	}

	
	@Override
	public ArrayList<Automobile> getAutomobiliWhereProprietarioPiuDi40anni() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		ArrayList<Automobile> result = new ArrayList<Automobile>();
		Automobile temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select a.*, DATEDIFF(CURDATE(), p.data_nascita)/365 as data_diff\r\n"
				+ "from proprietario p\r\n"
				+ "inner join automobile a on a.fk_proprietario = p.id_proprietario\r\n"
				+ "having data_diff > 40")) {

			while (rs.next()) {
				temp = new Automobile();

				temp.setIdAutomobile(rs.getLong("id_automobile"));				
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setCilindrata(rs.getInt("cilindrata"));
				temp.setDataImmatricolazione(rs.getDate("data_immatricolazione"));	
				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
		return result;
	}
	
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;		
	}

}
