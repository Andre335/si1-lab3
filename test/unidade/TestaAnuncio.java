package unidade;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Anunciante;
import models.Anuncio;
import models.EstiloGosta;
import models.EstiloNaoGosta;
import models.Instrumento;

import org.junit.Before;
import org.junit.Test;

public class TestaAnuncio {

	List<EstiloGosta> gosta;
	List<EstiloNaoGosta> naoGosta;
	List<Instrumento> instrumentos;
	String cidade, bairro;
	String facebook, email;
	String titulo, descricao;
	
	@Before
	public void setUp() {
		titulo = "Banda de Rock";
		descricao = "Banda para shows";
		cidade = "Campina Grande";
		bairro = "Bodocongo";
		email = "andre335@gmail.com";
		facebook = "facebook.com/andre335";
		
		gosta = new ArrayList<>();
		gosta.add(new EstiloGosta("Rock"));
		gosta.add(new EstiloGosta("Punk"));
		
		naoGosta = new ArrayList<>();
		naoGosta.add(new EstiloNaoGosta("Swingueira"));
		naoGosta.add(new EstiloNaoGosta("Axe"));
		
		instrumentos = new ArrayList<>();
		instrumentos.add(new Instrumento("Bateria"));
		instrumentos.add(new Instrumento("Violao"));
	}
	
    @Test
    public void testaNaoCriaAnuncioSemContato() {
        try {
        	new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, null, null);
        	fail("Esperava Excecao");
        } catch(Exception e) {
        	assertEquals("Informe ao menos uma forma de contato!", e.getMessage().toString());
        }
        
        try {
        	new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, "", null);
        	fail("Esperava Excecao");
        } catch(Exception e) {
        	assertEquals("Informe ao menos uma forma de contato!", e.getMessage().toString());
        }
        
        try {
        	new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, null, "");
        	fail("Esperava Excecao");
        } catch(Exception e) {
        	assertEquals("Informe ao menos uma forma de contato!", e.getMessage().toString());
        }
        
        try {
        	new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, "", "");
        	fail("Esperava Excecao");
        } catch(Exception e) {
        	assertEquals("Informe ao menos uma forma de contato!", e.getMessage().toString());
        }
    }
    
    @Test
    public void testaCriaAnuncioComApenasUmaFormaDeContato() {
    	Anuncio anuncio = new Anuncio();
    	Anunciante anunciante = new Anunciante();
    	
    	try {
			anunciante = new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, null, email);
			anuncio = new Anuncio(titulo, descricao, anunciante);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			anunciante = new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, true, facebook, null);
			anuncio = new Anuncio(titulo, descricao, anunciante);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	assertNotNull(anunciante);
    	assertNotNull(anuncio);
    	assertEquals(anuncio.getTitulo(), titulo);
    	assertEquals(anunciante.getCidade(), cidade);
    }

}
