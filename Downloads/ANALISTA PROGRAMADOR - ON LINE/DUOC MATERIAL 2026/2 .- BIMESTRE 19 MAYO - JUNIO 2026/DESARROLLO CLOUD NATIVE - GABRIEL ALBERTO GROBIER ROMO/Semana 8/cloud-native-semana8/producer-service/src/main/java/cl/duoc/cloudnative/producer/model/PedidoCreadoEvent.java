package cl.duoc.cloudnative.producer.model;

public record PedidoCreadoEvent(
    String eventId,
    String pedidoId,
    String usuarioId,
    String producto,
    int cantidad,
    String email,
    String roles
) {}


