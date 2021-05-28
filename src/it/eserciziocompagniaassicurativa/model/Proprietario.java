package it.eserciziocompagniaassicurativa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Proprietario {
	
	private Long idProprietario;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private List<Automobile> automobili = new ArrayList<>();	
	
	public Proprietario(long long1, String string, String string2, Date date) {
		// TODO Auto-generated constructor stub
	}
	public Proprietario() {
		// TODO Auto-generated constructor stub
	}
	public Long getIdProprietario() {
		return idProprietario;
	}
	public void setIdProprietario(Long idProprietario) {
		this.idProprietario = idProprietario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public List<Automobile> getAutomobili() {
		return this.automobili;
	}

	@Override
	public String toString() {
		return "Proprietario [idProprietario=" + idProprietario + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", automobili=" + automobili + "]";
	}
	
}
