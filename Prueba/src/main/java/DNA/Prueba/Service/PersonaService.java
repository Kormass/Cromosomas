package DNA.Prueba.Service;

import org.springframework.http.ResponseEntity;

import DNA.Prueba.Repository.entity.Persona;

public interface PersonaService {
    Persona crearPersona(String nombre, String apellido, String direccion, String email, String cromosoma);

    Persona obtenerPersonaPorId(Long id);

    Persona actualizarPersona(Long id, String nombre, String apellido, String direccion, String email, String cromosoma);

    void eliminarPersona(Long id);

    ResponseEntity<String> buscarSospechoso(String muestraADN);
}
