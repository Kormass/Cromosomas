package DNA.Prueba.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import DNA.Prueba.Repository.entity.Persona;
import DNA.Prueba.Service.PersonaService;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id).orElse(null);
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    } 
}


   


