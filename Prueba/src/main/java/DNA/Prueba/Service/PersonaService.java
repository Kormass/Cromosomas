package DNA.Prueba.Service;

import java.util.List;
import java.util.Optional;

import DNA.Prueba.Repository.entity.Persona;

public interface PersonaService {

    List<Persona> getAllPersonas();

    Optional<Persona> getPersonaById(Long id);

    Persona savePersona(Persona persona);

    void deletePersona(Long id);

    Optional<Persona> findByCromosoma(String muestraCromosoma);
}

