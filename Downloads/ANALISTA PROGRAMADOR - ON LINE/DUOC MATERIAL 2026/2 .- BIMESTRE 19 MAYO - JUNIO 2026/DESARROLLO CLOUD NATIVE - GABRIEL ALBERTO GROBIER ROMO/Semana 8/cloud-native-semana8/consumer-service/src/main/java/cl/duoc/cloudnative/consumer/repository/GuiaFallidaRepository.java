package cl.duoc.cloudnative.consumer.repository;

import cl.duoc.cloudnative.consumer.model.GuiaFallida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaFallidaRepository extends JpaRepository<GuiaFallida, Long> {
}