package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import models.Anunciante;
import models.Anuncio;
import models.EstiloGosta;
import models.EstiloNaoGosta;
import models.Instrumento;
import models.dao.GenericDAO;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	private static final GenericDAO dao = new GenericDAO();
	private Anuncio anuncioCriado;
	private Anunciante anunciante;
	
	@Transactional
    public Result index() {
        return ok(index.render(dao.findAllByClass(Anuncio.class)));
    }
    
    public Result anuncie() {
    	return ok(anuncie.render(null));
    }
    
    @Transactional
    public Result anunciar() {
        Map<String, String> mapDados = Form.form().bindFromRequest().data();
        Boolean procuraBanda;

        // dados do anuncio
        String titulo = mapDados.get("titulo");
        String descricao = mapDados.get("descricao");

        // dados do anunciante
        String cidade = mapDados.get("cidade");
        String bairro = mapDados.get("bairro");
        String facebook = mapDados.get("facebook");
        String email = mapDados.get("email");

        if (mapDados.get("interesse").equals("sim")) {
            procuraBanda = true;
        } else {
            procuraBanda = false;
        }

        // instrumentos e estilos
        List<Instrumento> instrumentos = new ArrayList<>();
        String[] instrumentosArray = mapDados.get("instrumentos").split(",");
        Instrumento instrumento;
        
        List<EstiloGosta> gosta = new ArrayList<>();
        String[] gostaArray = mapDados.get("gosta").split(",");
        EstiloGosta estiloGosta;
        
        List<EstiloNaoGosta> naoGosta = new ArrayList<>();
        String[] naoGostaArray = mapDados.get("naoGosta").split(",");
        EstiloNaoGosta estiloNaoGosta;
        
        for (int i = 0; i < gostaArray.length; i++) {
        	estiloGosta = new EstiloGosta(gostaArray[i]);
			gosta.add(estiloGosta);
		}
        
        for (int i = 0; i < naoGostaArray.length; i++) {
        	estiloNaoGosta = new EstiloNaoGosta(naoGostaArray[i]);
			naoGosta.add(estiloNaoGosta);
		}
        
        for (int i = 0; i < instrumentosArray.length; i++) {
			instrumento = new Instrumento(instrumentosArray[i]);
			instrumentos.add(instrumento);
		}
        
        try {
        	anunciante = new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, 
        		procuraBanda, facebook, email);
        	dao.persist(anunciante);
        } catch (Exception e) {
        	return badRequest(anuncie.render(e.getMessage().toString()));
        }
        
        anuncioCriado = new Anuncio(titulo, descricao, anunciante);
        dao.persist(anuncioCriado);
        dao.flush();
        return ok(index.render(dao.findAllByClass(Anuncio.class)));
    }
    
    @Transactional
    public Result filtrar() {
    	DynamicForm searchType = Form.form().bindFromRequest();
    	
    	if (searchType.get("pesquisa").equals("PalavraChave")) {
    		Query consultaPalavraChave = dao.createQuery("FROM Anuncio a WHERE a.titulo LIKE :palavra OR a.descricao LIKE :palavra");
			consultaPalavraChave.setParameter("palavra", "%" + searchType.get("valuePesquisa") + "%");
			
			return ok(index.render(consultaPalavraChave.getResultList()));
    	} else if (searchType.get("pesquisa").equals("Instrumento")) {
    		Query consultaInstrumentos = dao.createQuery("SELECT a FROM Anuncio a, Instrumento i WHERE i.nome = :instrumento");
			consultaInstrumentos.setParameter("instrumento", searchType.get("valuePesquisa"));
			
			List<Anuncio> listAnuncio = new ArrayList<>();
			for (Object anuncio : consultaInstrumentos.getResultList()) {
				for (Instrumento instrumento : ((Anuncio) anuncio).getAnunciante().getInstrumentos()) {
					if (searchType.get("valuePesquisa").toLowerCase().equals(instrumento.getNome().toLowerCase()) 
							&& !listAnuncio.contains(((Anuncio) anuncio))) {
						listAnuncio.add(((Anuncio) anuncio));
					}
				}
			}
			
			return ok(index.render(listAnuncio));
    	} else if (searchType.get("pesquisa").equals("Estilo")) {
    		Query consultaInstrumentos = dao.createQuery("SELECT a FROM Anuncio a, EstiloGosta e WHERE e.nome = :estilo");
			consultaInstrumentos.setParameter("estilo", searchType.get("valuePesquisa"));
			
			List<Anuncio> listAnuncio = new ArrayList<>();
			for (Object anuncio : consultaInstrumentos.getResultList()) {
				for (EstiloGosta estilo : ((Anuncio) anuncio).getAnunciante().getGosta()) {
					if (searchType.get("valuePesquisa").toLowerCase().equals(estilo.getNome().toLowerCase()) 
							&& !listAnuncio.contains(((Anuncio) anuncio))) {
						listAnuncio.add(((Anuncio) anuncio));
					}
				}
			}
			
			return ok(index.render(listAnuncio));
    	}
    	
    	return ok(index.render(dao.findAllByClass(Anuncio.class)));
    }

}
