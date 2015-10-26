package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Anunciante;
import models.Anuncio;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	Anuncio anuncio;
	Anunciante anunciante;
	
    public Result index() {
        return ok(index.render());
    }
    
    public Result anuncie() {
    	return ok(anuncie.render());
    }
    
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
        
        anunciante = new Anunciante(cidade, bairro, instrumentos, gosta, naoGosta, 
        		procuraBanda, facebook, email);
        anuncio = new Anuncio(titulo, descricao, anunciante);
        return ok(anuncios.render(titulo));
    }

}
