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
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Instrumento> instrumentos;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EstiloGosta> gosta;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EstiloNaoGosta> naoGosta;
	
	@Column(nullable = false)
	private boolean procuraBanda;
	
	@Column
	private String facebook;
	
	@Column
	private String email;
	
	public Anunciante () { 
		this.gosta = new ArrayList<EstiloGosta>();
		this.naoGosta = new ArrayList<EstiloNaoGosta>();
	}
	
	public Anunciante(String cidade, String bairro, List<Instrumento> instrumentos, List<EstiloGosta> gosta,
			List<EstiloNaoGosta> naoGosta, boolean procuraBanda, String facebook, String email) throws Exception {
		
		if (isContatoEmpty(facebook) && isContatoEmpty(email)) 
			throw new Exception ("Informe ao menos uma forma de contato!");
		
		this.cidade = cidade;
		this.bairro = bairro;
		this.procuraBanda = procuraBanda;
		
		this.instrumentos = instrumentos.isEmpty() ? new ArrayList<>() : instrumentos;
		this.gosta = gosta.isEmpty() ? new ArrayList<>() : gosta;
		this.naoGosta = naoGosta.isEmpty() ? new ArrayList<>() : naoGosta;

		
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

	public List<Instrumento> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public List<EstiloGosta> getGosta() {
		return gosta;
	}

	public void setGosta(List<EstiloGosta> gosta) {
		this.gosta = gosta;
	}

	public List<EstiloNaoGosta> getNaoGosta() {
		return naoGosta;
	}

	public void setNaoGosta(List<EstiloNaoGosta> naoGosta) {
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
		String gostaS = "Gosto de tocar: ";
		for (EstiloGosta estilo : this.gosta) {
			gostaS += estilo.getNome() + ", ";
		}
		return gostaS;
	}
	
	public String getNaoGostaString() {
		String naoGostaS = "Nao me interessa: ";
		for (EstiloNaoGosta estilo : this.naoGosta) {
			naoGostaS += estilo.getNome() + ", ";
		}
		return naoGostaS;
	}
	
	public String getInstrumentosString() {
		String instrumentoS = "Sei tocar: ";
		for (Instrumento instrumento : instrumentos) {
			instrumentoS += instrumento.getNome() + ",";
		}
		return instrumentoS;
	}
}
