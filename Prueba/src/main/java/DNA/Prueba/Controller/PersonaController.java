package DNA.Prueba.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DNA.Prueba.Service.PersonaService;

@RestController
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/buscar-sospechoso")
    public ResponseEntity<String> buscarSospechoso(@RequestParam String muestraADN) {
        ResponseEntity<String> sospechoso = personaService.buscarSospechoso(muestraADN);
        if (sospechoso != null) {
            return new ResponseEntity<>("El sospechoso es: " + sospechoso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró ningún sospechoso.", HttpStatus.NOT_FOUND);
        }
    }
}

