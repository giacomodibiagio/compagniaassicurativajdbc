package it.eserciziocompagniaassicurativa.dao.compagniaassicurativa;

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

public class CompagniaAssicurativaDAOImpl extends AbstractMySQLDAO implements ICompagniaAssicurativaDAO {

	@Override
	public List<CompagniaAssicurativa> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<CompagniaAssicurativa> result = new ArrayList<CompagniaAssicurativa>();
		CompagniaAssicurativa temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia_assicurativa")) {

			while (rs.next()) {
				temp = new CompagniaAssicurativa();
		
				temp.setIdCompagniaAssicurativa(rs.getLong("id_compagnia_assicurativa"));
				temp.setRagioneSociale(rs.getString("ragione_sociale"));
				temp.setIndirizzo(rs.getString("indirizzo"));
				temp.setDataFondazione(rs.getDate("data_fondazione"));		
				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public CompagniaAssicurativa get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		CompagniaAssicurativa result = new CompagniaAssicurativa();
		
		try (PreparedStatement ps = connection.prepareStatement("sselect * \r\n"
				+ "from compagnia_assicurativa\r\n"
				+ "where id_compagnia_assicurativa = ?;")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new CompagniaAssicurativa(					
					rs.getInt("id_compagnia_assicurativa"),
					rs.getString("ragione_sociale"),
					rs.getString("indirizzo"),
					rs.getDate("data_fondazione"));	
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
	public int update(CompagniaAssicurativa input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdCompagniaAssicurativa() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("UPDATE compagnia_assicurativa SET ragione_sociale=?, data_fondazione=?, indirizzo=? where id_compagnia_assicurativa=?;")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setDate(2, input.getDataFondazione());
			ps.setString(3, input.getIndirizzo());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setLong(4, input.getIdCompagniaAssicurativa());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(CompagniaAssicurativa input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		if(input.getIdCompagniaAssicurativa() == null || input.getIdCompagniaAssicurativa() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO compagnia_assicurativa (ragione_sociale,data_fondazione,indirizzo) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setDate(2, input.getDataFondazione());
			ps.setString(3, input.getIndirizzo());	
			// quando si fa il setDate serve un tipo java.sql.Date	
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public int delete(CompagniaAssicurativa input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdCompagniaAssicurativa() == null || input.getIdCompagniaAssicurativa() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia_assicurativa WHERE id_compagnia_assicurativa=?")) {
			ps.setLong(1, input.getIdCompagniaAssicurativa());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<CompagniaAssicurativa> findByExample(CompagniaAssicurativa input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<CompagniaAssicurativa> result = new ArrayList<CompagniaAssicurativa>();
		CompagniaAssicurativa compagniaAssicurativaTemp = null;

		String query = "select * from compagnia_assicurativa where 1=1 ";
		
		if (input.getRagioneSociale() != null && !input.getRagioneSociale().equals("")) {
			query += " and ragione_sociale ='" + input.getRagioneSociale() + "' ";
		}
		
		if (input.getIndirizzo() != null && !input.getIndirizzo().equals("")) {
			query += " and indirizzo ='" + input.getIndirizzo() + "' ";
		}

		if(input.getDataFondazione() != null)
			query += " and data_fondazione ='" + input.getDataFondazione().getTime() + "' ";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				compagniaAssicurativaTemp = new CompagniaAssicurativa();
				compagniaAssicurativaTemp.setRagioneSociale(rs.getString("ragione_sociale"));
				compagniaAssicurativaTemp.setIndirizzo(rs.getString("indirizzo"));
				compagniaAssicurativaTemp.setDataFondazione(rs.getDate("data_fondazione"));
				compagniaAssicurativaTemp.setIdCompagniaAssicurativa(rs.getLong("id_compagnia_assicurativa"));
				
				result.add(compagniaAssicurativaTemp);
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

	@Override
	public void riempiListaAutomobili(CompagniaAssicurativa input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		try (PreparedStatement ps = connection.prepareStatement(
				"select a.id_automobile, a.marca, a.modello, a.cilindrata, a.data_immatricolazione,\r\n"
				+ "p.id_proprietario, p.nome, p.cognome, p.data_nascita\r\n"
				+ "from compagnia_assicurativa c\r\n"
				+ "inner join automobile a on a.fk_compagnia_assicurativa = c.id_compagnia_assicurativa\r\n"
				+ "inner join proprietario p on a.fk_proprietario = p.id_proprietario\r\n"
				+ "where fk_compagnia_assicurativa = ?;")) {
			
			ps.setLong(1, input.getIdCompagniaAssicurativa());
			ResultSet rs = ps.executeQuery();
			//ArrayList<Automobile> result = new ArrayList<>();
			
			while(rs.next()) {
				Automobile temp = new Automobile();
				temp.setIdAutomobile(rs.getLong("id_automobile"));
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setCilindrata(rs.getInt("cilindrata"));		
				temp.setDataImmatricolazione(rs.getDate("data_immatricolazione"));
				temp.setProprietario(new Proprietario(rs.getLong("id_proprietario"),
													rs.getString("nome"),
													rs.getString("cognome"),
													rs.getDate("data_nascita")));
				
				input.getAutomobili().add(temp);
			}
			
			for(Automobile automobileItem: input.getAutomobili())
				System.out.println(automobileItem);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
}
