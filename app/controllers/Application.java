package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Anunciante;
import models.Anuncio;
import models.dao.GenericDAO;
import play.*;
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
    	return ok(anuncie.render());
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
        String instrumentos = mapDados.get("instrumentos");
        String gosta = mapDados.get("gosta");
        String naoGosta = mapDados.get("naoGosta");
        
        try {
        	anunciante = new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, 
        		procuraBanda, facebook, email);
        	dao.persist(anunciante);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        anuncioCriado = new Anuncio(titulo, descricao, anunciante);
        dao.persist(anuncioCriado);
        dao.flush();
        return ok(index.render(dao.findAllByClass(Anuncio.class)));
    }

}
