package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Instrumento")
public class Instrumento {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String nome;
	
	public Instrumento() {	}
	
	public Instrumento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
