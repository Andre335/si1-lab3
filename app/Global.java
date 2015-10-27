


import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Anunciante;
import models.Anuncio;
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

					if (i % 2 == 0) {
						anuncio.setTitulo("Quero tocar numa banda de rock");
						anuncio.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis nisi.");
						
						anunciante.setCidade("Campina Grande");
						anunciante.setBairro("Bateria");
						anunciante.setEmail("andre335@gmail.com");
						anunciante.setFacebook("facebook.com/andre335");
						anunciante.setGosta("rock, mpb");
						anunciante.setNaoGosta("swingueira, axe");
						anunciante.setInstrumentos("bateria, violao");
						anunciante.setProcuraBanda(true);
						
						dao.persist(anunciante);
						dao.flush();
						
						anuncio.setAnunciante(anunciante);
						
						dao.persist(anuncio);
						dao.flush();
					} else {
						anuncio.setTitulo("Quero tocar ocasionalmente");
						anuncio.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis nisi.");
						
						anunciante.setCidade("Joao Pessoa");
						anunciante.setBairro("Valentina");
						anunciante.setEmail("andre335@gmail.com");
						anunciante.setFacebook("facebook.com/andre335");
						anunciante.setGosta("rock, mpb");
						anunciante.setNaoGosta("swingueira, axe");
						anunciante.setInstrumentos("bateria, violao");
						anunciante.setProcuraBanda(false);
						
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