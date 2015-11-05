


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Anunciante;
import models.Anuncio;
import models.EstiloGosta;
import models.EstiloNaoGosta;
import models.Instrumento;
import models.dao.GenericDAO;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

public class Global extends GlobalSettings {
	private static GenericDAO dao = new GenericDAO();

	public void onStart(Application app) {
		Logger.info("Aplicacao inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@SuppressWarnings("resource")
			@Override
			public void invoke() throws Throwable {
				/*
				 * Teste
				 */
				for (int i = 0; i < 10; i++) {
					Anuncio anuncio = new Anuncio();
					Anunciante anunciante = new Anunciante();
					List<EstiloGosta> gosta = new ArrayList<>();
					List<EstiloNaoGosta> naoGosta = new ArrayList<>();
					List<Instrumento> instrumentos = new ArrayList<>();
					
					if (i % 2 == 0) {
						anuncio.setTitulo("Quero tocar numa banda de rock");
						anuncio.setDescricao("Insteressados em tocar em banda de rock para dar shows.");
						
						anunciante.setCidade("Campina Grande");
						anunciante.setBairro("Bodocongo");
						anunciante.setEmail("andre335@gmail.com");
						anunciante.setFacebook("facebook.com/andre335");
						anunciante.setProcuraBanda(true);
						
						instrumentos.add(new Instrumento("Bateria"));
						instrumentos.add(new Instrumento("Violao"));
						anunciante.setInstrumentos(instrumentos);
						
						gosta.add(new EstiloGosta("Rock"));
						gosta.add(new EstiloGosta("Mpb"));
						anunciante.setGosta(gosta);
						
						naoGosta.add(new EstiloNaoGosta("swingueira"));
						naoGosta.add(new EstiloNaoGosta("axe"));
						anunciante.setNaoGosta(naoGosta);
						dao.persist(anunciante);
						dao.flush();
						
						anuncio.setAnunciante(anunciante);
						dao.persist(anuncio);
						dao.flush();
					} else {
						anuncio.setTitulo("Quero tocar ocasionalmente");
						anuncio.setDescricao("Para quem quer se unir entre amigos para fazer um luau.");
						
						anunciante.setCidade("Joao Pessoa");
						anunciante.setBairro("Valentina");
						anunciante.setEmail("andre335@gmail.com");
						anunciante.setFacebook("facebook.com/andre335");
						anunciante.setProcuraBanda(false);
						
						instrumentos.add(new Instrumento("Bateria"));
						instrumentos.add(new Instrumento("Violao"));
						anunciante.setInstrumentos(instrumentos);
						
						gosta.add(new EstiloGosta("Rock"));
						gosta.add(new EstiloGosta("Mpb"));
						anunciante.setGosta(gosta);
						
						naoGosta.add(new EstiloNaoGosta("swingueira"));
						naoGosta.add(new EstiloNaoGosta("axe"));
						anunciante.setNaoGosta(naoGosta);
						dao.persist(anunciante);
						dao.flush();
						
						anuncio.setAnunciante(anunciante);
						dao.persist(anuncio);
						dao.flush();
					}
				}
			}
		});
	}

	public void onStop(Application app) {
		Logger.info("Aplicacao desligada...");
	}
}