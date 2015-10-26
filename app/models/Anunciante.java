package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Anunciante")
public class Anunciante {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String cidade;
	
	@Column
	private String bairro;
	
	@Column
	private String instrumentos;
	
	@Column
	private String gosta;
	
	@Column
	private String naoGosta;
	
	@Column
	private boolean procuraBanda;
	
	@Column
	private String facebook;
	
	@Column
	private String email;
	
	public Anunciante () { }
	
	public Anunciante(String cidade, String bairro, String instrumentos, String gosta,
			String naoGosta, boolean procuraBanda, String facebook, String email) throws Exception {
		
		if (isContatoEmpty(facebook) && isContatoEmpty(email)) 
			throw new Exception ("Informe ao menos uma forma de contato!");
		
		this.cidade = cidade;
		this.bairro = bairro;
		this.instrumentos = instrumentos;
		this.gosta = gosta;
		this.naoGosta = naoGosta;
		this.procuraBanda = procuraBanda;
		this.facebook = facebook;
		this.email = email;
	}

	private boolean isContatoEmpty(String contato) {
		if (contato == null || contato.equals("")) return true;
		else return false;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(String instrumentos) {
		this.instrumentos = instrumentos;
	}

	public String getGosta() {
		return gosta;
	}

	public void setGosta(String gosta) {
		this.gosta = gosta;
	}

	public String getNaoGosta() {
		return naoGosta;
	}

	public void setNaoGosta(String naoGosta) {
		this.naoGosta = naoGosta;
	}

	public boolean isProcuraBanda() {
		return procuraBanda;
	}

	public void setProcuraBanda(boolean procuraBanda) {
		this.procuraBanda = procuraBanda;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
