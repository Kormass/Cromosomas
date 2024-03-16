package DNA.Prueba.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DNA.Prueba.Repository.PersonaRepository;
import DNA.Prueba.Repository.entity.Persona;

import java.util.List;

@Component
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    

    @GetMapping("/buscar-sospechoso")
    public ResponseEntity<String> buscarSospechoso(@RequestParam String muestraADN) {
        List<Persona> personas = personaRepository.findAll();
        Persona sospechoso = null;
        double maxPorcentaje = 0;

        for (Persona persona : personas) {
            double porcentaje = calcularPorcentajeSimilitud(persona.getCromosoma(), muestraADN);
            if (porcentaje > maxPorcentaje) {
                maxPorcentaje = porcentaje;
                sospechoso = persona;
            }
        }

        if (sospechoso != null && maxPorcentaje > 80) {
            String mensaje = "El sospechoso es: " + sospechoso.getNombre() + ", con un " + maxPorcentaje + "% de similitud.\n";
            mensaje += "ID: " + sospechoso.getId() + "\n";
            mensaje += "Nombre: " + sospechoso.getNombre() + "\n";
            mensaje += "Apellido: " + sospechoso.getApellido() + "\n";
            mensaje += "Dirección: " + sospechoso.getDireccion() + "\n";
            mensaje += "Email: " + sospechoso.getEmail() + "\n";
            mensaje += "Cromosoma: " + sospechoso.getCromosoma() + "\n";
            
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private double calcularPorcentajeSimilitud(String cromosomaPersona, String muestraADN) {
        int coincidencias = 0;
        for (int i = 0; i < cromosomaPersona.length(); i++) {
            if (cromosomaPersona.charAt(i) == muestraADN.charAt(i)) {
                coincidencias++;
            }
        }
        return ((double) coincidencias / cromosomaPersona.length()) * 100;
    }

    @PostMapping("/personas")
    public Persona crearPersona(String nombre, String apellido, String direccion, String email, String cromosoma) {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDireccion(direccion);
        persona.setEmail(email);
        persona.setCromosoma(cromosoma);

        return personaRepository.save(persona);
    }

    @GetMapping("/personas/{id}")
    public Persona obtenerPersonaPorId(@PathVariable Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @PutMapping("/personas/{id}")
    public Persona actualizarPersona(@PathVariable Long id, String nombre, String apellido, String direccion, String email,
            String cromosoma) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDireccion(direccion);
            persona.setEmail(email);
            persona.setCromosoma(cromosoma);

            return personaRepository.save(persona);
        }
        return null;
    }

    @DeleteMapping("/personas/{id}")
    public void eliminarPersona(@PathVariable Long id) {
        personaRepository.deleteById(id);
    }

}