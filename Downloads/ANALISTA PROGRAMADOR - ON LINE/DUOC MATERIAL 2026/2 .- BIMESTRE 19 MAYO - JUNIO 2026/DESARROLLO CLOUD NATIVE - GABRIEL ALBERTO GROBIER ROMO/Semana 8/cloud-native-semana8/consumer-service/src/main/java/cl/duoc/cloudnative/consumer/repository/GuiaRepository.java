package cl.duoc.cloudnative.consumer.repository;

import cl.duoc.cloudnative.consumer.model.Guia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaRepository extends JpaRepository<Guia, Long> {
    Guia findByPedidoId(String pedidoId);
}