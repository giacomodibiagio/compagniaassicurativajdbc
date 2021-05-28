package it.eserciziocompagniaassicurativa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CompagniaAssicurativa {
	
	private Long idCompagniaAssicurativa;
	private String ragioneSociale;
	private Date dataFondazione;
	private String indirizzo;
	private List<Automobile> automobili = new ArrayList<>();
	
	
	


	public CompagniaAssicurativa(long long1, String string, String string2, Date date) {
		// TODO Auto-generated constructor stub
	}



	public CompagniaAssicurativa() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdCompagniaAssicurativa() {
		return idCompagniaAssicurativa;
	}
	public void setIdCompagniaAssicurativa(Long idCompagniaAssicurativa) {
		this.idCompagniaAssicurativa = idCompagniaAssicurativa;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public Date getDataFondazione() {
		return dataFondazione;
	}
	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public List<Automobile> getAutomobili() {
		return automobili;
	}

	@Override
	public String toString() {
		return "CompagniaAssicurativa [idCompagniaAssicurativa=" + idCompagniaAssicurativa + ", ragioneSociale="
				+ ragioneSociale + ", dataFondazione=" + dataFondazione + ", indirizzo=" + indirizzo + ", automobili="
				+ automobili + "]";
	}	
	
}
