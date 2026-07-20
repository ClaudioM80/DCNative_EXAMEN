package cl.duoc.cloudnative.inscripcion.repository;

import cl.duoc.cloudnative.inscripcion.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
