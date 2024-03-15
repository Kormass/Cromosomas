package DNA.Prueba.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DNA.Prueba.Repository.PersonaRepository;
import DNA.Prueba.Repository.entity.Persona;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return (List<Persona>) personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    public Optional<Persona> findByCromosoma(String muestraCromosoma) {
        return ((Collection<Persona>) personaRepository.findAll())
                .stream()
                .max((p1, p2) -> contarCoincidencias(p1.getCromosoma(), muestraCromosoma)
                                    - contarCoincidencias(p2.getCromosoma(), muestraCromosoma));
    }

    private int contarCoincidencias(String cromosoma, String muestraCromosoma) {
        int coincidencias = 0;
        for (int i = 0; i < cromosoma.length(); i++) {
            if (cromosoma.charAt(i) == muestraCromosoma.charAt(i)) {
                coincidencias++;
            }
        }
        return coincidencias;
    }
}
