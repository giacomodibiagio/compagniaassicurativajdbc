package it.eserciziocompagniaassicurativa.model;

import java.sql.Date;

public class Automobile {
	
	private Long idAutomobile;
	private String marca;
	private String modello;
	private Integer cilindrata;
	private Date dataImmatricolazione;
	private CompagniaAssicurativa compagniaAssicurativa;
	private Proprietario proprietario;
		
	public CompagniaAssicurativa getCompagniaAssicurativa() {
		return compagniaAssicurativa;
	}
	public void setCompagniaAssicurativa(CompagniaAssicurativa compagniaAssicurativa) {
		this.compagniaAssicurativa = compagniaAssicurativa;
	}
	public Proprietario getProprietario() {
		return proprietario;
	}
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	public Long getIdAutomobile() {
		return idAutomobile;
	}
	public void setIdAutomobile(Long idAutomobile) {
		this.idAutomobile = idAutomobile;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public Integer getCilindrata() {
		return cilindrata;
	}
	public void setCilindrata(Integer cilindrata) {
		this.cilindrata = cilindrata;
	}
	public Date getDataImmatricolazione() {
		return dataImmatricolazione;
	}
	public void setDataImmatricolazione(Date dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}
	
	@Override
	public String toString() {
		return "Automobile [idAutomobile=" + idAutomobile + ", marca=" + marca + ", modello=" + modello
				+ ", cilindrata=" + cilindrata + ", dataImmatricolazione=" + dataImmatricolazione
				+ ", compagniaAssicurativa=" + compagniaAssicurativa + ", proprietario=" + proprietario + "]";
	}
	
	
	
	
}
