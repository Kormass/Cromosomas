package DNA.Prueba.Inizializr;



import DNA.Prueba.Service.PersonaServiceImpl;
import DNA.Prueba.Repository.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonaServiceImpl personaService;

    @Override
    public void run(String... args) throws Exception {
        personaService.crearPersona("Pedro", "Gómez", "Calle 123", "pedro@example.com", "00000101010101010101");
        personaService.crearPersona("Juan", "Pérez", "Av. Principal", "juan@example.com", "00101010101101110111");
        personaService.crearPersona("Diego", "López", "Plaza Central", "diego@example.com", "00100010010000001001");

        Persona personaObtenida = personaService.obtenerPersonaPorId(1L);
        System.out.println("Persona obtenida por ID:");
        System.out.println(personaObtenida);

        personaService.actualizarPersona(2L, "Nuevo Nombre", "Nuevo Apellido", "Nueva Dirección", "nuevo@example.com", "10101010101010101010");

        personaService.eliminarPersona(3L);

        String muestraADN = "00000101010101010111"; 
        ResponseEntity<String> sospechoso = personaService.buscarSospechoso(muestraADN);
        System.out.println("Sospechoso encontrado:");
        System.out.println(sospechoso);
    }
}

/* Esta parte le pedi a ChatGPT que me diera datos de prueba para probar la aplicacion */
