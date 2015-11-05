import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.JsonNode;

import models.Anunciante;
import models.EstiloGosta;
import models.EstiloNaoGosta;
import models.Instrumento;
import models.dao.GenericDAO;

import org.junit.*;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.i18n.Lang;
import play.twirl.api.Content;
import scala.Option;
import static play.test.Helpers.*;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
	
	private static FakeApplication bandFinder;
	private EntityManager entityManager;
	private static final GenericDAO dao = new GenericDAO();
	
	@BeforeClass
	public static void startApp() {
		bandFinder = Helpers.fakeApplication(new Global());
		Helpers.start(bandFinder);
	}
	
	@Before
	public void inicializacao() {
        Option<JPAPlugin> pluginJPA = bandFinder.getWrappedApplication().plugin(JPAPlugin.class);
        entityManager = pluginJPA.get().em("default");
        JPA.bindForCurrentThread(entityManager);
        entityManager.getTransaction().begin();
	}
	
//	@Test
//	public void renderizarIndex() {
//		Result resultado = callAction(controllers.Application.index());
//		assertEquals(resultado, OK);
//	}
//	
//	@Test
//	public void renderizarAnunciar() {
//		Result resultado = callAction(controllers.Application.anuncie());
//		assertEquals(resultado, OK);
//	}
	
	@After
	public void close() {
        entityManager.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        entityManager.close();
	}
	
	@AfterClass
	public static void fechar() {
		Helpers.stop(bandFinder);
	}
}
