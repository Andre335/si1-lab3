package models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Andre on 01/06/2015.
 */
@Entity(name="Anuncio")
public class Anuncio {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
    private String titulo;
	
	@Column(nullable = false)
    private String descricao;
	
	@OneToOne(orphanRemoval = true)
    private Anunciante anunciante;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar date = Calendar.getInstance();
    
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public String getDateString() {
		String dateString = "";
		dateString += getDiaString() + "/" + getMesString() + "/" + getAnoString();
		return dateString;
	}

	private String getDiaString() {
		int dia = getDate().get(Calendar.DAY_OF_MONTH);
		return "" + dia;
	}
	
	private String getMesString() {
		int mes = getDate().get(Calendar.MONTH) + 1;
		return "" + mes;
	}
	private String getAnoString() {
		int ano = getDate().get(Calendar.YEAR);
		return "" + ano;
	}
}
