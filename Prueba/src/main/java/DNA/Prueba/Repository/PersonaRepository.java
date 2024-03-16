package DNA.Prueba.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DNA.Prueba.Repository.entity.Persona;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
   
}



