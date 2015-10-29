package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="Anunciante")
public class Anunciante {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String instrumentos;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Estilo> gosta;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Estilo> naoGosta;
	
	@Column(nullable = false)
	private boolean procuraBanda;
	
	@Column
	private String facebook;
	
	@Column
	private String email;
	
	public Anunciante () { 
		this.gosta = new ArrayList<Estilo>();
		this.gosta = new ArrayList<Estilo>();
	}
	
	public Anunciante(String cidade, String bairro, String instrumentos, List<Estilo> gosta,
			List<Estilo> naoGosta, boolean procuraBanda, String facebook, String email) throws Exception {
		
		if (isContatoEmpty(facebook) && isContatoEmpty(email)) 
			throw new Exception ("Informe ao menos uma forma de contato!");
		
		this.cidade = cidade;
		this.bairro = bairro;
		this.instrumentos = instrumentos;
		this.procuraBanda = procuraBanda;
		
		this.gosta = gosta.isEmpty() ? new ArrayList<>() : gosta;
//		if (gosta.isEmpty()) {
//			this.gosta = new ArrayList<>();
//		} else {
//			this.gosta = gosta;
//		}
		
		this.naoGosta = naoGosta.isEmpty() ? new ArrayList<>() : naoGosta;
//		if (naoGosta.isEmpty()) {
//			this.naoGosta = new ArrayList<>();
//		} else {
//			this.naoGosta = naoGosta;
//		}
		
		if (isContatoEmpty(facebook)) {
			setFacebook("Nao informado");
			this.email = email;
		} else if (isContatoEmpty(email)){
			setEmail("Nao informado");
			this.facebook = facebook;
		} else {
			this.email = email;
			this.facebook = facebook;
		}
		
	}

	private boolean isContatoEmpty(String contato) {
		if (contato == null || contato.trim().equals("")) return true;
		return false;
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

	public List<Estilo> getGosta() {
		return gosta;
	}

	public void setGosta(List<Estilo> gosta) {
		this.gosta = gosta;
	}

	public List<Estilo> getNaoGosta() {
		return naoGosta;
	}

	public void setNaoGosta(List<Estilo> naoGosta) {
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
	
	public String getGostaString() {
		String gostaS = "";
		for (Estilo estilo : this.gosta) {
			gostaS += estilo.getNome() + ", ";
		}
		return gostaS;
	}
	
	public String getNaoGostaString() {
		String naoGostaS = "";
		for (Estilo estilo : this.gosta) {
			naoGostaS += estilo.getNome() + ", ";
		}
		return naoGostaS;
	}
	
}
