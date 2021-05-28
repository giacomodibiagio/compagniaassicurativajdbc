package it.eserciziocompagniaassicurativa.dao.proprietario;

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


public class ProprietarioDAOImpl extends AbstractMySQLDAO implements IProprietarioDAO {

	@Override
	public List<Proprietario> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Proprietario> result = new ArrayList<Proprietario>();
		Proprietario temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from proprietario;")) {

			while (rs.next()) {
				temp = new Proprietario();
				temp.setIdProprietario(rs.getLong("id_proprietario"));
				temp.setNome(rs.getString("nome"));
				temp.setCognome(rs.getString("cognome"));
				temp.setDataNascita(rs.getDate("data_nascita"));				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Proprietario get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Proprietario result = new Proprietario();
		
		try (PreparedStatement ps = connection.prepareStatement("sselect * from proprietario where id_proprietario = ?;")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Proprietario();
					result.setIdProprietario(rs.getLong("id_proprietario"));
					result.setNome(rs.getString("nome"));
					result.setCognome(rs.getString("cognome"));
					result.setDataNascita(rs.getDate("data_nascita"));					
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
	public int update(Proprietario input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdProprietario() == null || input.getIdProprietario() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("UPDATE proprietario SET nome=?, cognome=?, data_nascita=? where id_proprietario=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setDate(3, input.getDataNascita());
			// quando si fa il setDate serve un tipo java.sql.Date

			ps.setLong(4, input.getIdProprietario());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Proprietario input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		if(input.getIdProprietario() == null || input.getIdProprietario() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO proprietario (nome,cognome,data_nascita) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setDate(3, input.getDataNascita());	
			// quando si fa il setDate serve un tipo java.sql.Date	
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public int delete(Proprietario input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getIdProprietario() == null || input.getIdProprietario() <1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM proprietario WHERE id_proprietario=?")) {
			ps.setLong(1, input.getIdProprietario());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Proprietario> findByExample(Proprietario input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Proprietario> result = new ArrayList<Proprietario>();
		Proprietario proprietarioTemp = null;

		String query = "select * from proprietario where 1=1 ";
		
		if (input.getNome() != null && !input.getNome().equals("")) {
			query += " and nome ='" + input.getNome() + "' ";
		}
		
		if (input.getCognome()!= null && !input.getCognome().equals("")) {
			query += " and cognome ='" + input.getCognome() + "' ";
		}

		if(input.getDataNascita() != null)
			query += " and data_nascita ='" + input.getDataNascita().getTime() + "' ";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				proprietarioTemp = new Proprietario();
				proprietarioTemp.setNome(rs.getString("nome"));
				proprietarioTemp.setCognome(rs.getString("cognome"));
				proprietarioTemp.setDataNascita(rs.getDate("data_nascita"));
				proprietarioTemp.setIdProprietario(rs.getLong("id_proprietario"));
				
				result.add(proprietarioTemp);
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
	public void riempiListaAutomobili(Proprietario input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		try (PreparedStatement ps = connection.prepareStatement(
				"select a.id_automobile, a.marca, a.modello, a.cilindrata, a.data_immatricolazione,\r\n"
				+ "c.id_compagnia_assicurativa, c.ragione_sociale, c.indirizzo, c.data_fondazione\r\n"
				+ "from proprietario p\r\n"
				+ "inner join automobile a on a.fk_proprietario = p.id_proprietario\r\n"
				+ "inner join compagnia_assicurativa c on a.fk_compagnia_assicurativa = c.id_compagnia_assicurativa "
				+ "where fk_proprietario = ?;")) {
			
			ps.setLong(1, input.getIdProprietario());
			ResultSet rs = ps.executeQuery();
		
			
			while(rs.next()) {
				Automobile temp = new Automobile();
				temp.setIdAutomobile(rs.getLong("id_automobile"));
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setCilindrata(rs.getInt("cilindrata"));		
				temp.setDataImmatricolazione(rs.getDate("data_immatricolazione"));
				temp.setCompagniaAssicurativa(new CompagniaAssicurativa(rs.getLong("id_compagnia_assicurativa"),
																		rs.getString("ragione_sociale"),
																		rs.getString("indirizzo"),
																		rs.getDate("data_fondazione")));
				
				input.getAutomobili().add(temp);
			}
			
			for(Automobile automobileItem: input.getAutomobili())
				System.out.println(automobileItem);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ArrayList<Proprietario> getProprietariWhereAutomobileHaCompagniaAMilano() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Proprietario> result = new ArrayList<Proprietario>();
		Proprietario temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select p.*\r\n"
				+ "from proprietario p\r\n"
				+ "inner join automobile a on a.fk_proprietario = p.id_proprietario\r\n"
				+ "inner join compagnia_assicurativa c on a.fk_compagnia_assicurativa = c.id_compagnia_assicurativa\r\n"
				+ "where c.indirizzo like '%Milano';")) {

			while (rs.next()) {
				temp = new Proprietario();
				temp.setIdProprietario(rs.getLong("id_proprietario"));
				temp.setNome(rs.getString("nome"));
				temp.setCognome(rs.getString("cognome"));
				temp.setDataNascita(rs.getDate("data_nascita"));				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public ArrayList<Proprietario> getProprietariWhereCompagniaMenoDiVentiAutomobili() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Proprietario> result = new ArrayList<Proprietario>();
		Proprietario temp = null;		

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select p.*\r\n"
				+ "from proprietario p\r\n"
				+ "inner join automobile a\r\n"
				+ "inner join compagnia_assicurativa c on  p.id_proprietario = a.fk_proprietario\r\n"
				+ "and c.id_compagnia_assicurativa = a.fk_compagnia_assicurativa \r\n"
				+ "where c.id_compagnia_assicurativa in ( "
				+ "		select c.id_compagnia_assicurativa "
				+ "		from proprietario p\r\n"
				+ " 	    inner join automobile a\r\n"
				+ "  		inner join compagnia_assicurativa c on  p.id_proprietario = a.fk_proprietario\r\n"
				+ "   		and c.id_compagnia_assicurativa  = a.fk_compagnia_assicurativa \r\n"
				+ "   		group by c.id_compagnia_assicurativa\r\n"
				+ "			having count(a.id_automobile) < 20)\r\n"
				+ " group by p.id_proprietario;")) {

			while (rs.next()) {
				temp = new Proprietario();
				temp.setIdProprietario(rs.getLong("id_proprietario"));
				temp.setNome(rs.getString("nome"));
				temp.setCognome(rs.getString("cognome"));
				temp.setDataNascita(rs.getDate("data_nascita"));				
				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
}
