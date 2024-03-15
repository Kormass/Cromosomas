package DNA.Prueba.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import DNA.Prueba.Repository.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {
}

