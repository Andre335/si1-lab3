package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by Andre on 01/06/2015.
 */
@Entity(name="Anuncio")
public class Anuncio {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
    private String titulo;
	
	@Column
    private String descricao;
	
	@ManyToOne
    private Anunciante anunciante;
    
    public Anuncio() { }
    
    public Anuncio(String titulo, String descricao, Anunciante anunciante) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setAnunciante(anunciante);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
